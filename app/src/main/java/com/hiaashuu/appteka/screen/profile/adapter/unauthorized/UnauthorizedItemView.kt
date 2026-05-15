package com.hiaashuu.appteka.screen.profile.adapter.unauthorized

import android.view.View
import android.widget.TextView
import com.hiaashuu.appteka.util.adapter.BaseItemViewHolder
import com.hiaashuu.appteka.util.adapter.ItemView
import com.hiaashuu.appteka.R

interface UnauthorizedItemView : ItemView {

    fun setOnLoginButtonClickListener(listener: (() -> Unit)?)

}

class UnauthorizedItemViewHolder(view: View) : BaseItemViewHolder(view), UnauthorizedItemView {

    private val loginButton: TextView = view.findViewById(R.id.login_button)

    private var loginButtonClickListener: (() -> Unit)? = null

    init {
        loginButton.setOnClickListener { loginButtonClickListener?.invoke() }
    }

    override fun setOnLoginButtonClickListener(listener: (() -> Unit)?) {
        this.loginButtonClickListener = listener
    }

    override fun onUnbind() {
        this.loginButtonClickListener = null
    }

}