package com.hiaashuu.appteka.screen.gallery.di

import com.hiaashuu.appteka.screen.gallery.GalleryActivity
import com.hiaashuu.appteka.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [GalleryModule::class])
interface GalleryComponent {

    fun inject(activity: GalleryActivity)

}