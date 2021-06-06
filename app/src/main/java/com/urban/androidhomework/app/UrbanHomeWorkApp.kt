package com.urban.androidhomework.app

import android.app.Application
import com.urban.androidhomework.di.AppComponent
import com.urban.androidhomework.di.AppModule
import com.urban.androidhomework.di.DaggerAppComponent

class UrbanHomeWorkApp : Application() {

    lateinit var component: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        component.inject(this)
    }

    companion object {
        lateinit var instance: UrbanHomeWorkApp
            private set
    }

}