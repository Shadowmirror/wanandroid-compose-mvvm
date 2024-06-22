package miao.kmirror.wanandroid.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import miao.kmirror.wanandroid.base.AppViewModel
import miao.kmirror.wanandroid.base.BaseAppViewModel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {
    @Provides
    @Singleton
    fun provideBaseAppViewModel(): BaseAppViewModel{
        return BaseAppViewModel()
    }


    @Provides
    @Singleton
    fun provideAppViewModel():AppViewModel{
        return AppViewModel()
    }
}