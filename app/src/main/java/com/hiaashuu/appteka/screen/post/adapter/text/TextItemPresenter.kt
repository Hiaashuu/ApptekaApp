package com.hiaashuu.appteka.screen.post.adapter.text

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.post.adapter.ItemListener

class TextItemPresenter(
    private val listener: ItemListener,
) : ItemPresenter<TextItemView, TextItem> {

    override fun bindView(view: TextItemView, item: TextItem, position: Int) {
        with(view) {
            setMaxLength(item.maxLength)
            setText(item.text)
            if (item.errorRequiredField) {
                showRequiredFieldError()
            } else {
                hideRequiredFieldError()
            }
            setOnTextChangedListener { listener.onTextChanged(it) }
        }
    }

}