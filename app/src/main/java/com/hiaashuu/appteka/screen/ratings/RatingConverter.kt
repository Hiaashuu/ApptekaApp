package com.hiaashuu.appteka.screen.ratings

import com.hiaashuu.appteka.core.permissions.CapabilityAction
import com.hiaashuu.appteka.core.permissions.CapabilityPolicy
import com.hiaashuu.appteka.screen.details.api.RatingEntity
import com.hiaashuu.appteka.screen.ratings.adapter.rating.RatingItem
import com.hiaashuu.appteka.user.api.UserBrief

interface RatingConverter {

    fun convert(entity: RatingEntity, brief: UserBrief?): RatingItem

}

class RatingConverterImpl : RatingConverter {

    override fun convert(entity: RatingEntity, brief: UserBrief?): RatingItem {

        val showRatingMenu = CapabilityPolicy.isAllowed(
            action = CapabilityAction.APP_RATING_DELETE,
            capabilities = entity.capabilities,
            allowOnUnknown = legacyCanDelete(brief, entity.user.id),
        )
        return RatingItem(
            id = entity.rateId.toLong(),
            rateId = entity.rateId,
            score = entity.score,
            text = entity.text,
            time = entity.time * 1000,
            user = entity.user,
            showRatingMenu = showRatingMenu,
        )
    }

    private fun legacyCanDelete(brief: UserBrief?, ratingUserId: Int): Boolean {
        val u = brief ?: return false
        return u.role >= LEGACY_ROLE_ADMIN || u.id == ratingUserId
    }

}

private const val LEGACY_ROLE_ADMIN = 200