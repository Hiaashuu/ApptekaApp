package com.hiaashuu.appteka.screen.details.adapter.header

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.categories.DEFAULT_LOCALE
import com.hiaashuu.appteka.download.AWAIT
import com.hiaashuu.appteka.download.COMPLETED
import com.hiaashuu.appteka.download.ERROR
import com.hiaashuu.appteka.download.IDLE
import com.hiaashuu.appteka.download.STARTED
import com.hiaashuu.appteka.screen.details.adapter.ItemListener
import java.util.Locale

class HeaderItemPresenter(
    private val locale: Locale,
    private val listener: ItemListener,
) : ItemPresenter<HeaderItemView, HeaderItem> {

    override fun bindView(view: HeaderItemView, item: HeaderItem, position: Int) {
        when (item.downloadState) {
            IDLE -> view.hideProgress()
            AWAIT -> view.setIndeterminate()
            STARTED -> view.setIndeterminate()
            COMPLETED -> view.hideProgress()
            ERROR -> view.hideProgress()
            else -> view.setProgress(item.downloadState)
        }
        view.setAppIcon(item.icon)
        view.setAppLabel(item.label)
        view.setAppPackage(item.packageName)
        val author = item.author
        val icon = author?.icon
        if (author != null && icon != null) {
            view.showUploader()
            view.setUploaderIcon(icon)
            view.setUploaderBadge(author.primaryBadge)

            val name = author.name.takeIf { !it.isNullOrBlank() }
                ?: icon.label?.get(locale.language)
                ?: icon.label?.get(DEFAULT_LOCALE).orEmpty()
            view.setUploaderName(name)

            view.setOnUploaderClickListener { listener.onProfileClick(author.id) }
        } else {
            view.hideUploader()
        }
    }

}