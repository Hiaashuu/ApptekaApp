package com.hiaashuu.appteka.screen.upload.adapter.selected_app

import android.content.pm.PackageManager
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.upload.adapter.ItemListener
import com.hiaashuu.appteka.util.createApkIconURI
import com.hiaashuu.appteka.util.getLabel

class SelectedAppItemPresenter(
    private val listener: ItemListener,
    private val resourceProvider: SelectedAppResourceProvider,
    private val packageManager: PackageManager,
) : ItemPresenter<SelectedAppItemView, SelectedAppItem> {

    override fun bindView(view: SelectedAppItemView, item: SelectedAppItem, position: Int) {
        with(view) {
            val uri = createApkIconURI(item.apk.path)
            setAppIcon(uri)
            setAppLabel(item.apk.packageInfo.getLabel(packageManager))
            setAppPackage(item.apk.packageInfo.packageName)
            setAppVersion(item.apk.version)
            setAppSize(resourceProvider.formatFileSize(item.apk.size))
        }
        view.setOnClickListener { listener.onSelectAppClick() }
        view.setOnDiscardListener { listener.onDiscardClick() }
    }

}