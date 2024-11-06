package miao.kmirror.wanndroid.compose.viewmodel.main

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import miao.kmirror.wanndroid.compose.bean.Article
import miao.kmirror.wanndroid.compose.bean.Banner
import miao.kmirror.wanndroid.compose.network.WanAndroidApi
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    val bannerList = mutableStateListOf<Banner>()
    val articleList = mutableStateListOf<Article>()

    @Inject
    lateinit var mWanAndroidApi: WanAndroidApi
    fun initData() {
        loadBanner()
        loadArticleBanner()
    }


    fun loadBanner() {
        viewModelScope.launch {
            bannerList.addAll(mWanAndroidApi.getBanner().data)
        }
    }


    fun loadArticleBanner() {
        viewModelScope.launch {
            articleList.addAll(mWanAndroidApi.getArticle().data.datas)
        }
    }
}