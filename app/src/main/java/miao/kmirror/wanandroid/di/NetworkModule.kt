package miao.kmirror.wanandroid.di

import android.content.Context
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import miao.kmirror.wanandroid.http.CacheInterceptor
import miao.kmirror.wanandroid.http.WanAndroidService
import miao.kmirror.wanandroid.http.logInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache, cookieJar: PersistentCookieJar): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .cache(cache)
            .addInterceptor(CacheInterceptor(30))
            .connectTimeout(10, TimeUnit.SECONDS)
            .cookieJar(cookieJar)
            .build()
    }

    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(context.cacheDir, cacheSize.toLong())
    }


    @Provides
    @Singleton
    fun provideCookieJar(@ApplicationContext context: Context): PersistentCookieJar {
        return PersistentCookieJar(
            SetCookieCache(),
            SharedPrefsCookiePersistor(context)
        )
    }


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://www.wanandroid.com")
            .build()
    }


    @Provides
    @Singleton
    fun provideWanAndroidService(retrofit: Retrofit): WanAndroidService {
        return retrofit.create(WanAndroidService::class.java)
    }
}