package com.hiaashuu.appteka.screen.profile.adapter.unauthorized

import com.hiaashuu.appteka.util.adapter.ItemPresenter
import com.hiaashuu.appteka.screen.profile.adapter.ItemListener

class UnauthorizedItemPresenter(
    private val listener: ItemListener,
) : ItemPresenter<UnauthorizedItemView, UnauthorizedItem> {

    override fun bindView(view: UnauthorizedItemView, item: UnauthorizedItem, position: Int) {
        view.setOnLoginButtonClickListener { listener.onLoginClick() }
    }

}