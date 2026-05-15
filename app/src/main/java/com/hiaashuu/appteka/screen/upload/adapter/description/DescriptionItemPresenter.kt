package com.hiaashuu.appteka.screen.upload.adapter.description

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.upload.DescriptionValidatorImpl.Companion.MIN_DESCRIPTION_LENGTH
import com.hiaashuu.appteka.screen.upload.adapter.ItemListener

class DescriptionItemPresenter(
    private val listener: ItemListener,
) : ItemPresenter<DescriptionItemView, DescriptionItem> {

    override fun bindView(view: DescriptionItemView, item: DescriptionItem, position: Int) {
        with(view) {
            setText(item.text)
            when {
                item.errorRequiredField -> showRequiredFieldError()
                item.errorMinLength -> showMinLengthError(MIN_DESCRIPTION_LENGTH)
                else -> hideError()
            }
            setOnTextChangedListener { listener.onDescriptionChanged(it) }
        }
    }

}