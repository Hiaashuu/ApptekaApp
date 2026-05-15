package com.hiaashuu.appteka.screen.details.adapter.controls

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.download.AWAIT
import com.hiaashuu.appteka.download.COMPLETED
import com.hiaashuu.appteka.download.ERROR
import com.hiaashuu.appteka.download.IDLE
import com.hiaashuu.appteka.download.STARTED
import com.hiaashuu.appteka.screen.details.adapter.ItemListener
import com.hiaashuu.appteka.util.NOT_INSTALLED

class ControlsItemPresenter(
    private val listener: ItemListener,
) : ItemPresenter<ControlsItemView, ControlsItem> {

    override fun bindView(view: ControlsItemView, item: ControlsItem, position: Int) {
        view.hideButtons()
        view.enableButtons()
        val isProgress = when (item.downloadState) {
            IDLE -> false
            AWAIT -> true
            STARTED -> true
            COMPLETED -> false
            ERROR -> false
            else -> true
        }
        when {
            item.installedVersionCode == NOT_INSTALLED -> {
                view.showInstallButton()
                if (isProgress) {
                    view.showCancelButton()
                    view.disableInstallButton()
                }
            }

            item.installedVersionCode < item.versionCode -> {
                if (isProgress) {
                    view.showCancelButton()
                    view.disableUpdateButton()
                } else {
                    view.showRemoveButton()
                    view.showUpdateButton()
                }
            }

            else -> {
                if (isProgress) {
                    view.showCancelButton()
                    view.disableLaunchButton()
                } else {
                    view.showRemoveButton()
                    view.showLaunchButton()
                }
            }
        }
        view.setOnInstallClickListener { listener.onInstallClick() }
        view.setOnUpdateClickListener { listener.onInstallClick() }
        view.setOnLaunchClickListener { listener.onLaunchClick(item.packageName) }
        view.setOnRemoveClickListener { listener.onRemoveClick(item.packageName) }
        view.setOnCancelClickListener { listener.onCancelClick(item.appId) }
    }

}