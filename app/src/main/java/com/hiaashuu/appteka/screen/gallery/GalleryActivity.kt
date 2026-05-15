package com.hiaashuu.appteka.screen.gallery

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.hiaashuu.appteka.util.adapter.ItemBinder
import com.hiaashuu.appteka.util.adapter.AdapterPresenter
import com.hiaashuu.appteka.util.adapter.SimpleRecyclerAdapter
import com.hiaashuu.appteka.appComponent
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.screen.gallery.di.GalleryModule
import com.hiaashuu.appteka.util.Analytics
import javax.inject.Inject

class GalleryActivity : AppCompatActivity(), GalleryPresenter.GalleryRouter {

    @Inject
    lateinit var presenter: GalleryPresenter

    @Inject
    lateinit var adapterPresenter: AdapterPresenter

    @Inject
    lateinit var binder: ItemBinder

    @Inject
    lateinit var analytics: Analytics

    private val saveFileLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.data?.let { uri ->
                    presenter.onSaveCurrentScreenshot(uri)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        val items = GalleryItem.deserializeList(intent.getStringExtra(EXTRA_ITEMS))
        if (items == null) {
            finish()
            return
        }
        val startIndex = intent.getIntExtra(EXTRA_START_INDEX, 0)

        val presenterState = savedInstanceState?.getBundle(KEY_PRESENTER_STATE)
        appComponent
            .galleryComponent(GalleryModule(this, items, startIndex, presenterState))
            .inject(activity = this)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
        )

        super.onCreate(savedInstanceState)
        setContentView(R.layout.gallery_activity)

        val adapter = SimpleRecyclerAdapter(adapterPresenter, binder)
        val view = GalleryViewImpl(window.decorView, adapter)

        presenter.attachView(view)

        if (savedInstanceState == null) {
            analytics.trackEvent("open-gallery-screen")
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.attachRouter(this)
    }

    override fun onStop() {
        presenter.detachRouter()
        super.onStop()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBundle(KEY_PRESENTER_STATE, presenter.saveState())
    }

    override fun openSaveScreenshotDialog(fileName: String, fileType: String) {
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT)
            .apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = fileType
                putExtra(Intent.EXTRA_TITLE, fileName)
            }
        saveFileLauncher.launch(intent)
    }

    override fun leaveScreen(success: Boolean) {
        if (success) {
            setResult(RESULT_OK)
        } else {
            setResult(RESULT_CANCELED)
        }
        finish()
    }

}

fun createGalleryActivityIntent(
    context: Context,
    items: List<GalleryItem>,
    startIndex: Int,
): Intent = Intent(context, GalleryActivity::class.java)
    .putExtra(EXTRA_ITEMS, GalleryItem.serializeList(items))
    .putExtra(EXTRA_START_INDEX, startIndex)

private const val EXTRA_ITEMS = "gallery_items"
private const val EXTRA_START_INDEX = "start_index"
private const val KEY_PRESENTER_STATE = "presenter_state"