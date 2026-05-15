package com.hiaashuu.appteka.screen.chat.adapter.outgoing

import android.text.util.Linkify
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat.getDrawable
import com.hiaashuu.appteka.util.adapter.BaseItemViewHolder
import com.hiaashuu.appteka.util.adapter.ItemView
import com.google.android.material.card.MaterialCardView
import com.hiaashuu.appteka.R
import com.hiaashuu.appteka.dto.BadgeMark
import com.hiaashuu.appteka.dto.UserIcon
import com.hiaashuu.appteka.screen.chat.adapter.MsgAttachment
import com.hiaashuu.appteka.screen.chat.view.MessageAttachmentsView
import com.hiaashuu.appteka.util.LinkMovementMethodCompat
import com.hiaashuu.appteka.util.bind
import com.hiaashuu.appteka.util.formatMessageText
import com.hiaashuu.appteka.view.UserIconView
import com.hiaashuu.appteka.view.UserIconViewImpl

interface OutgoingMsgItemView : ItemView {

    fun setUserIcon(userIcon: UserIcon)

    fun setUserBadge(badge: BadgeMark?)

    fun setTime(time: String)

    fun setDate(date: String?)

    fun setText(text: String)

    fun setAttachments(attachments: List<MsgAttachment>?)

    fun sendingState()

    fun sentState()

    fun deliveredState()

    fun setOnClickListener(listener: (() -> Unit)?)

    fun setOnAttachmentClickListener(listener: ((Int) -> Unit)?)

}

class OutgoingMsgItemViewHolder(view: View) : BaseItemViewHolder(view), OutgoingMsgItemView {

    private val resources = view.resources
    private val dateView: TextView = view.findViewById(R.id.message_date)
    private val memberIconContainer: View = view.findViewById(R.id.member_icon)
    private val userIconView: UserIconView = UserIconViewImpl(memberIconContainer)
    private val bubbleBack: MaterialCardView = view.findViewById(R.id.out_bubble_back)
    private val attachmentsView: MessageAttachmentsView = view.findViewById(R.id.out_attachments)
    private val delivery: ImageView = view.findViewById(R.id.message_delivery)
    private val textView: TextView = view.findViewById(R.id.out_text)
    private val timeView: TextView = view.findViewById(R.id.out_time)

    private var clickListener: (() -> Unit)? = null
    private var attachmentClickListener: ((Int) -> Unit)? = null

    init {
        bubbleBack.setOnClickListener { clickListener?.invoke() }
        memberIconContainer.setOnClickListener { clickListener?.invoke() }
    }

    override fun setUserIcon(userIcon: UserIcon) {
        userIconView.bind(userIcon)
    }

    override fun setUserBadge(badge: BadgeMark?) {
        userIconView.bindBadge(badge)
    }

    override fun setTime(time: String) {
        timeView.bind(time)
    }

    override fun setDate(date: String?) {
        dateView.bind(date)
    }

    override fun setText(text: String) {
        textView.visibility = if (text.isEmpty()) View.GONE else View.VISIBLE
        textView.text = formatMessageText(text, textView.context)
        val hasLinks = Linkify.addLinks(
            textView,
            Linkify.WEB_URLS or Linkify.EMAIL_ADDRESSES or Linkify.PHONE_NUMBERS
        )
        if (hasLinks) {
            textView.movementMethod = LinkMovementMethodCompat
        } else {
            textView.movementMethod = null
        }
        textView.isFocusable = false
        textView.isClickable = false
        textView.isLongClickable = false
    }

    override fun setAttachments(attachments: List<MsgAttachment>?) {
        if (attachments.isNullOrEmpty()) {
            attachmentsView.visibility = View.GONE
            return
        }
        attachmentsView.visibility = View.VISIBLE
        attachmentsView.setAttachments(attachments) { index ->
            attachmentClickListener?.invoke(index)
        }
    }

    override fun sendingState() {
        delivery.setImageDrawable(getDrawable(resources, R.drawable.clock, null))
    }

    override fun sentState() {
        delivery.setImageDrawable(getDrawable(resources, R.drawable.check_circle, null))
    }

    override fun deliveredState() {
        delivery.setImageDrawable(getDrawable(resources, R.drawable.check_all, null))
    }

    override fun setOnClickListener(listener: (() -> Unit)?) {
        this.clickListener = listener
    }

    override fun setOnAttachmentClickListener(listener: ((Int) -> Unit)?) {
        this.attachmentClickListener = listener
    }

    override fun onUnbind() {
        this.clickListener = null
        this.attachmentClickListener = null
    }

}