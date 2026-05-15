package com.hiaashuu.appteka.screen.chat.adapter.system

import com.hiaashuu.appteka.util.adapter.ItemPresenter

class SystemMsgItemPresenter : ItemPresenter<SystemMsgItemView, SystemMsgItem> {

    override fun bindView(view: SystemMsgItemView, item: SystemMsgItem, position: Int) {
        view.setText(item.text)
        view.setDate(item.date)
    }

}