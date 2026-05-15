package com.hiaashuu.appteka.screen.upload.adapter.prefill_version

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.upload.adapter.ItemListener

class PrefillVersionItemPresenter(
    private val listener: ItemListener
) : ItemPresenter<PrefillVersionItemView, PrefillVersionItem> {

    override fun bindView(view: PrefillVersionItemView, item: PrefillVersionItem, position: Int) {
        with(view) {
            setVersions(item.versions, item.selectedVersion)
            setOnVersionSelectedListener { version ->
                listener.onPrefillVersionSelected(version)
            }
        }
    }

}