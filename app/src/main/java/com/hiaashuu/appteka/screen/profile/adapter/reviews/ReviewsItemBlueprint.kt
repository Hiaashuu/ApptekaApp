package com.hiaashuu.appteka.screen.profile.adapter.reviews

import com.hiaashuu.appteka.util.adapter.ItemBinder
import com.hiaashuu.appteka.util.adapter.AdapterPresenter
import com.hiaashuu.appteka.util.adapter.SimpleRecyclerAdapter
import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R
import dagger.Lazy

class ReviewsItemBlueprint(
    override val presenter: ItemPresenter<ReviewsItemView, ReviewsItem>,
    private val adapterPresenter: Lazy<AdapterPresenter>,
    private val binder: Lazy<ItemBinder>,
) :
    ItemBlueprint<ReviewsItemView, ReviewsItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.profile_block_ratings,
        creator = { _, view ->
            ReviewsItemViewHolder(
                view,
                SimpleRecyclerAdapter(adapterPresenter.get(), binder.get())
            )
        }
    )

    override fun isRelevantItem(item: Item) = item is ReviewsItem

}