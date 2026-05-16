package com.hiaashuu.appteka.screen.store

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.hiaashuu.appteka.util.adapter.SimpleRecyclerAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jakewharton.rxrelay3.PublishRelay
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.util.ActionItem
import com.hiaashuu.appteka.util.ActionsAdapter
import com.hiaashuu.appteka.util.clicks
import com.hiaashuu.appteka.util.hideWithAlphaAnimation
import com.hiaashuu.appteka.util.showWithAlphaAnimation
import io.reactivex.rxjava3.core.Observable

interface StoreView {

    fun showProgress()

    fun showContent()

    fun contentUpdated()

    fun contentUpdated(position: Int)

    fun showPlaceholder()

    fun showError()

    fun showCategories(items: List<CategoryDropdownItem>)

    fun setSelectedCategory(item: CategoryDropdownItem)

    fun scrollToTop()

    fun stopPullRefreshing()

    fun isPullRefreshing(): Boolean

    fun retryClicks(): Observable<Unit>

    fun refreshClicks(): Observable<Unit>

    fun categorySelectedClicks(): Observable<Int>

    fun searchBarClicks(): Observable<Unit>

}

class StoreViewImpl(
    view: View,
    private val preferences: StorePreferencesProvider,
    private val adapter: SimpleRecyclerAdapter,
) : StoreView {

    private val context = view.context
    private val refresher: SwipeRefreshLayout = view.findViewById(R.id.swipe_refresh)
    private val flipper: ViewFlipper = view.findViewById(R.id.view_flipper)
    private val overlayProgress: View = view.findViewById(R.id.overlay_progress)
    private val recycler: RecyclerView = view.findViewById(R.id.recycler)
    private val error: TextView = view.findViewById(R.id.error_text)
    private val retryButton: View = view.findViewById(R.id.button_retry)
    

    private val retryRelay = PublishRelay.create<Unit>()
    private val refreshRelay = PublishRelay.create<Unit>()
    private val categorySelectedRelay = PublishRelay.create<Int>()
    private val searchBarRelay = PublishRelay.create<Unit>()

    init {
        val orientation = RecyclerView.VERTICAL
        val layoutManager = LinearLayoutManager(context, orientation, false)
        adapter.setHasStableIds(true)
        recycler.adapter = adapter
        recycler.layoutManager = layoutManager
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.itemAnimator?.changeDuration = DURATION_MEDIUM

        refresher.setOnRefreshListener { refreshRelay.accept(Unit) }

        retryButton.clicks(retryRelay)

        
    }

    override fun showProgress() {
        refresher.isEnabled = false
        flipper.displayedChild = CHILD_CONTENT
        overlayProgress.showWithAlphaAnimation(animateFully = true)
    }

    override fun showContent() {
        refresher.isEnabled = true
        flipper.displayedChild = CHILD_CONTENT
        overlayProgress.hideWithAlphaAnimation(animateFully = false)
    }

    override fun showPlaceholder() {
        refresher.isRefreshing = false
        refresher.isEnabled = true
        flipper.displayedChild = CHILD_PLACEHOLDER
    }

    override fun showError() {
        refresher.isEnabled = true
        flipper.displayedChild = CHILD_ERROR

        error.setText(R.string.load_files_error)
    }

    override fun showCategories(items: List<CategoryDropdownItem>) {
        val dialog = BottomSheetDialog(context)
        val actionView = View.inflate(context, R.layout.bottom_sheet_actions, null)
        val actionsRecycler: RecyclerView = actionView.findViewById(R.id.actions_recycler)

        val actions = items.map { item ->
            ActionItem(
                id = item.id,
                title = item.title,
                iconRes = item.iconRes,
                iconSvg = item.iconSvg
            )
        }

        val actionsAdapter = ActionsAdapter(actions) { itemId ->
            dialog.dismiss()
            val index = items.indexOfFirst { it.id == itemId }
            if (index >= 0) {
                categorySelectedRelay.accept(index)
            }
        }

        actionsRecycler.layoutManager = LinearLayoutManager(context)
        actionsRecycler.adapter = actionsAdapter

        dialog.setContentView(actionView)
        dialog.show()
    }

    override fun setSelectedCategory(item: CategoryDropdownItem) {
        val activity = context as? androidx.appcompat.app.AppCompatActivity
        val title = if (item.id == 0) context.getString(R.string.tab_store) else item.title
        activity?.supportActionBar?.title = title
    }

    override fun scrollToTop() {
        recycler.scrollToPosition(0)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun contentUpdated() {
        adapter.notifyDataSetChanged()
    }

    override fun contentUpdated(position: Int) {
        adapter.notifyItemChanged(position)
    }

    override fun stopPullRefreshing() {
        refresher.isRefreshing = false
    }

    override fun isPullRefreshing(): Boolean = refresher.isRefreshing

    override fun retryClicks(): Observable<Unit> = retryRelay

    override fun refreshClicks(): Observable<Unit> = refreshRelay

    override fun categorySelectedClicks(): Observable<Int> = categorySelectedRelay

    override fun searchBarClicks(): Observable<Unit> = searchBarRelay

}

private const val DURATION_MEDIUM = 300L
private const val CHILD_CONTENT = 0
private const val CHILD_PLACEHOLDER = 1
private const val CHILD_ERROR = 2