package com.hiaashuu.appteka.screen.details.adapter.user_review

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.categories.DEFAULT_LOCALE
import com.hiaashuu.appteka.screen.details.adapter.ItemListener
import java.text.DateFormat
import java.util.Locale

class UserReviewItemPresenter(
    private val dateFormatter: DateFormat,
    private val locale: Locale,
    private val listener: ItemListener,
) : ItemPresenter<UserReviewItemView, UserReviewItem> {

    override fun bindView(view: UserReviewItemView, item: UserReviewItem, position: Int) {
        item.user.icon?.let(view::setMemberIcon)
        view.setMemberBadge(item.user.primaryBadge)
        val name = item.user.name.takeIf { !it.isNullOrBlank() }
            ?: item.user.icon?.label?.get(locale.language)
            ?: item.user.icon?.label?.get(DEFAULT_LOCALE).orEmpty()
        view.setMemberName(name)
        view.setRating(item.score.toFloat())
        val date: String = dateFormatter.format(item.time)
        view.setDate(date)
        view.setReview(item.text)
        view.setOnEditListener { listener.onRateClick(item.score.toFloat(), item.text) }
    }

}