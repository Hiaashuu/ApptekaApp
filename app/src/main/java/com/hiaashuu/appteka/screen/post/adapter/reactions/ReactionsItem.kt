package com.hiaashuu.appteka.screen.post.adapter.reactions

import com.hiaashuu.appteka.util.adapter.Item
import com.hiaashuu.appteka.screen.feed.api.Reaction

data class ReactionsItem(
    override val id: Long,
    val availableReactions: List<Reaction>,
    val selectedReactionIds: Set<String>,
) : Item