package miao.kmirror.wanndroid.compose

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.imageLoader
import coil3.memory.MemoryCache
import miao.kmirror.wanndroid.compose.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.ksp.generated.module

class MyApp : Application(), SingletonImageLoader.Factory {


    companion object {
        private lateinit var instance: MyApp

        fun getInstance(): MyApp {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        this.imageLoader.memoryCache
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(AppModule().module)
        }
    }

    override fun newImageLoader(context: PlatformContext): ImageLoader {
        return ImageLoader.Builder(context)
            .memoryCache {
                MemoryCache.Builder()
                    .maxSizePercent(context)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(context.cacheDir.resolve("coil_cache"))
                    .build()
            }
            .build()
    }
}