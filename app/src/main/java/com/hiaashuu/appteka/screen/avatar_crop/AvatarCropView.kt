package com.hiaashuu.appteka.screen.avatar_crop

import android.graphics.Bitmap
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.doOnLayout
import com.avito.android.krop.KropView
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxrelay3.PublishRelay
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.util.hideWithAlphaAnimation
import com.hiaashuu.appteka.util.showWithAlphaAnimation
import io.reactivex.rxjava3.core.Observable

interface AvatarCropView {

    fun setBitmap(bitmap: Bitmap)

    fun readCroppedBitmap(): Bitmap?

    fun showProgress()

    fun showContent()

    fun showError(text: String)

    fun navigationClicks(): Observable<Unit>

    fun doneClicks(): Observable<Unit>

}

class AvatarCropViewImpl(view: View) : AvatarCropView {

    private val rootView: View = view.findViewById(R.id.root_view)
    private val toolbar: Toolbar = view.findViewById(R.id.toolbar)
    private val kropView: KropView = view.findViewById(R.id.krop_view)
    private val overlayProgress: View = view.findViewById(R.id.overlay_progress)

    private val navigationRelay = PublishRelay.create<Unit>()
    private val doneRelay = PublishRelay.create<Unit>()

    init {
        toolbar.setTitle(R.string.avatar_crop_title)
        toolbar.setNavigationOnClickListener { navigationRelay.accept(Unit) }
        toolbar.inflateMenu(R.menu.avatar_crop_menu)
        toolbar.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.menu_done) {
                doneRelay.accept(Unit)
                true
            } else false
        }
    }

    override fun setBitmap(bitmap: Bitmap) {

        kropView.doOnLayout {
            kropView.setBitmap(bitmap)
        }
    }

    override fun readCroppedBitmap(): Bitmap? = kropView.getCroppedBitmap()

    override fun showProgress() {
        overlayProgress.showWithAlphaAnimation(animateFully = true)
    }

    override fun showContent() {
        overlayProgress.hideWithAlphaAnimation(animateFully = false)
    }

    override fun showError(text: String) {
        Snackbar.make(rootView, text, Snackbar.LENGTH_LONG).show()
    }

    override fun navigationClicks(): Observable<Unit> = navigationRelay
    override fun doneClicks(): Observable<Unit> = doneRelay

}