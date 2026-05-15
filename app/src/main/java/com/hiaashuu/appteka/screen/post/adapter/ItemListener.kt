package com.hiaashuu.appteka.screen.post.adapter

import com.hiaashuu.appteka.screen.feed.api.Reaction
import com.hiaashuu.appteka.screen.post.adapter.image.ImageItem

interface ItemListener {

    fun onTextChanged(text: String)

    fun onSubmitClick()

    fun onScreenAppendClick()

    fun onImageClick(item: ImageItem)

    fun onImageDelete(item: ImageItem)

    fun onReactionClick(reaction: Reaction)

}