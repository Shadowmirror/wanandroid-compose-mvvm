
package miao.kmirror.wanndroid.compose.viewmodel.main

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import miao.kmirror.wanndroid.compose.bean.Article
import miao.kmirror.wanndroid.compose.bean.Banner
import miao.kmirror.wanndroid.compose.network.WanAndroidApiService
import org.koin.android.annotation.KoinViewModel



@KoinViewModel
class MainViewModel(
    private val mWanAndroidApiService: WanAndroidApiService
) : ViewModel() {
    val bannerList = mutableStateListOf<Banner>()
    val articleList = mutableStateListOf<Article>()
    private var curPage = 0

    fun initData() {
        loadBanner()
        loadArticleBanner()
    }


    @Suppress("MemberVisibilityCanBePrivate")
    fun loadBanner() {
        viewModelScope.launch {
            bannerList.addAll(mWanAndroidApiService.wanAndroidApi.getBanner().data)
        }
    }


    fun loadArticleBanner() {
        viewModelScope.launch {
            val responseData = mWanAndroidApiService.wanAndroidApi.getArticle(curPage)
            curPage = responseData.data.curPage
            articleList.addAll(responseData.data.datas)
        }
    }
}