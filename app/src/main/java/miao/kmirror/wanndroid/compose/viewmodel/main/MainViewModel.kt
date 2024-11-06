package miao.kmirror.wanndroid.compose.viewmodel.main

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private var curPage = 0

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
            val responseData = mWanAndroidApi.getArticle(curPage)
            curPage = responseData.data.curPage
            articleList.addAll(responseData.data.datas)
        }
    }
}