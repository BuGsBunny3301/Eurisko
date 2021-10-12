package org.smartmobiletech.euriskoapp

import android.app.Application

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        AppPreferences.init(this)
    }

}