package com.hiaashuu.appteka.screen.home

import android.content.Intent

interface HomeFragment {
    fun handleEvent(data: Intent?)
    fun onReselect() {}
}