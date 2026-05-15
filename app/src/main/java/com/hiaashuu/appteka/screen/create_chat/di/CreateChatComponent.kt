package com.hiaashuu.appteka.screen.create_chat.di

import com.hiaashuu.appteka.screen.create_chat.CreateChatActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [CreateChatModule::class])
interface CreateChatComponent {

    fun inject(activity: CreateChatActivity)

}