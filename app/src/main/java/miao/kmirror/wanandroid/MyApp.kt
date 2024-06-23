package miao.kmirror.wanandroid

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import dagger.hilt.android.HiltAndroidApp
import miao.kmirror.wanandroid.base.AppViewModel
import miao.kmirror.wanandroid.base.BaseAppViewModel
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltAndroidApp
class MyApp : Application(), ViewModelStoreOwner {

    companion object {
        var appContext: MyApp by Delegates.notNull()
    }

    @Inject
    lateinit var mBaseAppViewModel: BaseAppViewModel

    @Inject
    lateinit var mAppViewModel: AppViewModel

    override fun onCreate() {
        super.onCreate()
        appContext = this

    }

    override val viewModelStore: ViewModelStore
        get() = ViewModelStore()
}