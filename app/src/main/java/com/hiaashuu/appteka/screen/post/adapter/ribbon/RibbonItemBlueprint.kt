package com.hiaashuu.appteka.screen.post.adapter.ribbon

import com.hiaashuu.appteka.util.adapter.ItemBinder
import com.hiaashuu.appteka.util.adapter.AdapterPresenter
import com.hiaashuu.appteka.util.adapter.SimpleRecyclerAdapter
import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.util.adapter.ItemBlueprint
import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.util.adapter.ViewHolderBuilder
import com.hiaashuu.appteka.R
import dagger.Lazy

class RibbonItemBlueprint(
    override val presenter: ItemPresenter<RibbonItemView, RibbonItem>,
    private val adapterPresenter: Lazy<AdapterPresenter>,
    private val binder: Lazy<ItemBinder>,
) :
    ItemBlueprint<RibbonItemView, RibbonItem> {

    override val viewHolderProvider =
        ViewHolderBuilder.ViewHolderProvider(
            layoutId = R.layout.post_block_ribbon,
            creator = { _, view ->
                RibbonItemViewHolder(
                    view,
                    SimpleRecyclerAdapter(adapterPresenter.get(), binder.get())
                )
            }
        )

    override fun isRelevantItem(item: Item) = item is RibbonItem

}