package com.hiaashuu.appteka.screen.topics

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hiaashuu.appteka.util.adapter.ItemBinder
import com.hiaashuu.appteka.util.adapter.AdapterPresenter
import com.hiaashuu.appteka.util.adapter.SimpleRecyclerAdapter
import com.hiaashuu.appteka.appComponent
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.dto.TopicEntity
import com.hiaashuu.appteka.screen.auth.request_code.createRequestCodeActivityIntent
import com.hiaashuu.appteka.screen.chat.createChatActivityIntent
import com.hiaashuu.appteka.screen.home.HomeFragment
import com.hiaashuu.appteka.screen.topics.di.TopicsModule
import com.hiaashuu.appteka.util.Analytics
import com.hiaashuu.appteka.util.ZipParcelable
import com.hiaashuu.appteka.util.getParcelableCompat
import javax.inject.Inject

class TopicsFragment : Fragment(), TopicsPresenter.TopicsRouter, HomeFragment {

    @Inject
    lateinit var presenter: TopicsPresenter

    @Inject
    lateinit var adapterPresenter: AdapterPresenter

    @Inject
    lateinit var preferences: TopicsPreferencesProvider

    @Inject
    lateinit var binder: ItemBinder

    @Inject
    lateinit var analytics: Analytics

    override fun onCreate(savedInstanceState: Bundle?) {
        val presenterState = savedInstanceState
            ?.getParcelableCompat(KEY_PRESENTER_STATE, ZipParcelable::class.java)
            ?.restore<Bundle>()
        requireContext().appComponent
            .topicsComponent(TopicsModule(requireContext(), presenterState))
            .inject(fragment = this)

        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            analytics.trackEvent("open-topics-fragment")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.topics_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = SimpleRecyclerAdapter(adapterPresenter, binder)
        val topicsView = TopicsViewImpl(view, preferences, adapter)

        presenter.attachView(topicsView)
    }

    override fun onStart() {
        super.onStart()
        presenter.attachRouter(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.invalidate()
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
        outState.putParcelable(KEY_PRESENTER_STATE, ZipParcelable(presenter.saveState()))
    }

    override fun showChatScreen(entity: TopicEntity) {
        val intent = createChatActivityIntent(requireContext(), entity)
        startActivity(intent)
    }

    override fun openLoginScreen() {
        val intent = createRequestCodeActivityIntent(requireContext())
        startActivity(intent)
    }

    override fun handleEvent(data: Intent?) {}

    override fun onReselect() {
        presenter.scrollToTop()
    }

}

fun createTopicsFragment(): TopicsFragment = TopicsFragment()

private const val KEY_PRESENTER_STATE = "presenter_state"