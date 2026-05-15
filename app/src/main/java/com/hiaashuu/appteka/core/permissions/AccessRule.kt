package com.hiaashuu.appteka.core.permissions

object AccessRule {
    const val AUTOMODERATION = "automoderation"
    const val FEED_POST_DELETE = "feed_post_delete"
    const val LIST_DOWNLOADED = "list_downloaded"
    const val FINAL_MODER_VOTE = "final_moder_vote"
    const val APP_UNLINK = "app_unlink"
    const val APP_DELETE = "app_delete"
    const val APP_UNPUBLISH = "app_unpublish"
    const val APP_EDIT_META = "app_edit_meta"
    const val APP_RATING_DELETE = "app_rating_delete"
    const val CHAT_TOPIC_CREATE = "chat_topic_create"
    const val CHAT_IMAGE_ATTACH = "chat_image_attach"
    const val READ_ONLY_MESSAGES = "read_only_messages"
    const val READ_ONLY_RATINGS = "read_only_ratings"
    const val READ_ONLY_APP_UPLOAD = "read_only_app_upload"
    const val READ_ONLY_FEED_POSTS = "read_only_feed_posts"
    const val READ_ONLY_AVATAR_UPLOAD = "read_only_avatar_upload"
    const val READ_ONLY_BIO_EDIT = "read_only_bio_edit"
}

object CapabilityAction {

    const val CHAT_MESSAGE_SEND = "chat.message.send"
    const val CHAT_MESSAGE_DELETE = "chat.message.delete"
    const val CHAT_MESSAGE_REPORT = "chat.message.report"

    const val APP_RATE = "app.rate"
    const val APP_RATING_DELETE = "app.rating.delete"
    const val APP_EDIT_META = "app.edit_meta"
    const val APP_UNPUBLISH = "app.unpublish"
    const val APP_UNLINK = "app.unlink"
    const val APP_DELETE = "app.delete"

    const val FEED_POST_DELETE = "feed.post.delete"

    const val APP_UPLOAD = "app.upload"
    const val APP_UPLOAD_BYPASS_MODERATION = "app.upload.bypass_moderation"
    const val CHAT_TOPIC_CREATE = "chat.topic.create"
    const val CHAT_IMAGE_ATTACH = "chat.image.attach"
    const val FEED_POST_CREATE = "feed.post.create"
    const val MODERATION_ENTER = "moderation.enter"
    const val MODERATION_FINAL_VOTE = "moderation.final_vote"
    const val USER_ELIMINATE = "user.eliminate"
    const val AVATAR_UPLOAD = "avatar.upload"
    const val BIO_EDIT = "bio.edit"
}