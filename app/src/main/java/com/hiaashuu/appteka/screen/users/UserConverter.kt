package com.hiaashuu.appteka.screen.users

import com.hiaashuu.appteka.screen.users.adapter.UserItem
import com.hiaashuu.appteka.screen.users.adapter.subscriber.SubscriberItem
import com.hiaashuu.appteka.screen.users.api.PublisherEntity
import com.hiaashuu.appteka.screen.users.api.SubscriberEntity
import com.hiaashuu.appteka.screen.users.api.UserEntity
import java.util.concurrent.TimeUnit

interface UserConverter {

    fun convert(entity: UserEntity): UserItem

}

class UserConverterImpl : UserConverter {

    override fun convert(entity: UserEntity): UserItem {
        return when(entity) {
            is SubscriberEntity -> SubscriberItem(
                id = entity.rowId.toLong(),
                time = TimeUnit.SECONDS.toMillis(entity.time),
                user = entity.user,
            )
            is PublisherEntity -> SubscriberItem(
                id = entity.rowId.toLong(),
                time = TimeUnit.SECONDS.toMillis(entity.time),
                user = entity.user,
            )
            else -> throw IllegalArgumentException("Entity type is not supported: $entity")
        }
    }

}