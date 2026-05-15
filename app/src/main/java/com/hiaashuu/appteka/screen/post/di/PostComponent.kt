package com.hiaashuu.appteka.screen.post.di

import com.hiaashuu.appteka.screen.post.PostActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [PostModule::class])
interface PostComponent {

    fun inject(activity: PostActivity)

}