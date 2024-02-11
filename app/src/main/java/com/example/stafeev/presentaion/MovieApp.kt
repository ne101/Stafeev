package com.example.stafeev.presentaion

import android.app.Application
import com.example.stafeev.di.DaggerApplicationComponent

class MovieApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}