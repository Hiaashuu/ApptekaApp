package com.hiaashuu.appteka.screen.details.adapter

import com.hiaashuu.appteka.screen.details.adapter.play.PlaySecurityStatus
import com.hiaashuu.appteka.screen.details.adapter.screenshot.ScreenshotItem
import com.hiaashuu.appteka.screen.details.adapter.status.StatusAction

interface ItemListener {

    fun onProfileClick(userId: Int)

    fun onPermissionsClick(permissions: List<String>)

    fun onScoresClick()

    fun onInstallClick()

    fun onLaunchClick(packageName: String)

    fun onRemoveClick(packageName: String)

    fun onCancelClick(appId: String)

    fun onDiscussClick()

    fun onTranslateClick()

    fun onGooglePlayClick()

    fun onRateClick(rating: Float, review: String?)

    fun onVersionsClick()

    fun onStatusAction(type: StatusAction)

    fun onScreenshotClick(items: List<ScreenshotItem>, clicked: Int)

    fun onRequestSecurityScan(appId: String)

    fun onSecurityInfoClick(status: PlaySecurityStatus, score: Int?)

}