package com.hiaashuu.appteka.screen.upload.adapter.other_versions

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.upload.adapter.ItemListener

class OtherVersionsItemPresenter(
    private val listener: ItemListener,
) : ItemPresenter<OtherVersionsItemView, OtherVersionsItem> {

    override fun bindView(view: OtherVersionsItemView, item: OtherVersionsItem, position: Int) {
        with(view) {
            setVersionsCount(item.versions.size)
            setOnClickListener { listener.onOtherVersionsClick(item.versions) }
        }
    }

}