package com.hiaashuu.appteka.screen.post

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.screen.post.adapter.append.AppendItem
import com.hiaashuu.appteka.screen.post.adapter.image.ImageItem
import com.hiaashuu.appteka.screen.post.adapter.reactions.ReactionsItem
import com.hiaashuu.appteka.screen.post.adapter.ribbon.RibbonItem
import com.hiaashuu.appteka.screen.post.adapter.submit.SubmitItem
import com.hiaashuu.appteka.screen.post.adapter.text.TextItem
import com.hiaashuu.appteka.screen.post.dto.FeedConfig
import com.hiaashuu.appteka.screen.post.dto.PostImage
import com.hiaashuu.appteka.util.trim
import kotlin.math.min

interface PostConverter {

    fun convert(
        images: List<PostImage>,
        text: String,
        highlightErrors: Boolean,
        config: FeedConfig,
        selectedReactionIds: Set<String>,
    ): List<Item>

}

class PostConverterImpl() : PostConverter {

    override fun convert(
        images: List<PostImage>,
        text: String,
        highlightErrors: Boolean,
        config: FeedConfig,
        selectedReactionIds: Set<String>,
    ): List<Item> {
        var id: Long = 1
        val items = ArrayList<Item>()

        items += TextItem(
            id++,
            text = text.substring(0, min(text.length, config.postMaxLength)),
            errorRequiredField = highlightErrors && text.isBlank(),
            maxLength = config.postMaxLength,
        )

        items += RibbonItem(
            id = id++,
            items = images
                .map {
                    ImageItem(
                        id = it.longId(),
                        original = it.original,
                        preview = it.preview,
                        width = it.width,
                        height = it.height,
                        remote = it.remote()
                    )
                }
                .plus(AppendItem(id++))
                .trim(config.postMaxImages)
        )

        config.reactions?.takeIf { it.isNotEmpty() }?.let { availableReactions ->
            items += ReactionsItem(
                id = id++,
                availableReactions = availableReactions,
                selectedReactionIds = selectedReactionIds,
            )
        }

        items += SubmitItem(id++)

        return items
    }

    private fun PostImage.longId(): Long =
        (scrId?.hashCode() ?: original.toString().hashCode()).toLong()

    private fun PostImage.remote(): Boolean = scrId != null

}