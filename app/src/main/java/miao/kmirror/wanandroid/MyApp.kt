package miao.kmirror.wanandroid

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import miao.kmirror.wanandroid.base.AppViewModel
import miao.kmirror.wanandroid.base.BaseAppViewModel
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltAndroidApp
class MyApp : Application(), ViewModelStoreOwner {

    companion object {
        var appContext: Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this

    }

    override val viewModelStore: ViewModelStore
        get() = ViewModelStore()
}