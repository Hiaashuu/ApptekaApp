package com.hiaashuu.appteka.screen.users.adapter.subscriber

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.categories.DEFAULT_LOCALE
import com.hiaashuu.appteka.screen.users.adapter.ItemListener
import com.hiaashuu.appteka.screen.users.adapter.UsersResourceProvider
import java.util.Locale

class SubscriberItemPresenter(
    private val locale: Locale,
    private val resourceProvider: UsersResourceProvider,
    private val listener: ItemListener,
) : ItemPresenter<SubscriberItemView, SubscriberItem> {

    override fun bindView(view: SubscriberItemView, item: SubscriberItem, position: Int) {
        with(item) {
            if (hasMore) {
                hasMore = false
                hasProgress = true
                listener.onLoadMore(this)
            }
        }

        val name = item.user.name.takeIf { !it.isNullOrBlank() }
            ?: item.user.icon.label?.get(locale.language)
            ?: item.user.icon.label?.get(DEFAULT_LOCALE).orEmpty()
        view.setUserName(name)
        view.setUserIcon(item.user.icon)
        view.setUserBadge(item.user.primaryBadge)
        view.setSubscribedDate(resourceProvider.formatSubscribedDate(item.time))
        if (item.hasProgress) view.showProgress() else view.hideProgress()
        view.setOnClickListener { listener.onItemClick(item) }
    }

}