package com.hiaashuu.appteka.screen.chat.di

import com.hiaashuu.appteka.screen.chat.ChatActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [ChatModule::class])
interface ChatComponent {

    fun inject(activity: ChatActivity)

}