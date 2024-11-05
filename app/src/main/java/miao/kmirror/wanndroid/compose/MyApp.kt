package miao.kmirror.wanndroid.compose

import android.app.Application
import android.util.Log

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.i("Kmirror", "onCreate: MyApp")
    }
}