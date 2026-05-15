package com.hiaashuu.appteka.screen.agreement

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hiaashuu.appteka.appComponent
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.screen.agreement.di.AgreementModule
import com.hiaashuu.appteka.util.Analytics
import javax.inject.Inject

class AgreementActivity : AppCompatActivity(), AgreementPresenter.AgreementRouter {

    @Inject
    lateinit var presenter: AgreementPresenter

    @Inject
    lateinit var analytics: Analytics

    override fun onCreate(savedInstanceState: Bundle?) {
        val presenterState = savedInstanceState?.getBundle(KEY_PRESENTER_STATE)
        appComponent
            .agreementComponent(AgreementModule(presenterState))
            .inject(activity = this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.agreement_activity)

        val view = AgreementViewImpl(window.decorView)

        presenter.attachView(view)

        if (savedInstanceState == null) {
            analytics.trackEvent("open-agreement-screen")
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

    override fun leaveScreen(success: Boolean) {
        if (success) {
            setResult(RESULT_OK)
        } else {
            setResult(RESULT_CANCELED)
        }
        finish()
    }

}

fun createAgreementActivityIntent(
    context: Context,
): Intent = Intent(context, AgreementActivity::class.java)

private const val KEY_PRESENTER_STATE = "presenter_state"