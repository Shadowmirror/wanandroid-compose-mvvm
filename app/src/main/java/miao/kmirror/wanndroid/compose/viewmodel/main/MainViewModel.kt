package miao.kmirror.wanndroid.compose.viewmodel.main

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import miao.kmirror.wanndroid.compose.bean.Article
import miao.kmirror.wanndroid.compose.bean.Banner
import miao.kmirror.wanndroid.compose.repository.WanAndroidRepository
import org.koin.android.annotation.KoinViewModel


@KoinViewModel
class MainViewModel(
    private val wanAndroidRepository: WanAndroidRepository
) : ViewModel() {
    val bannerList = mutableStateListOf<Banner>()
    val articleList = mutableStateListOf<Article>()
    private var curPage = 0

    fun initData() {
        loadBanner()
        loadArticle()
    }


    private fun loadBanner() {
        viewModelScope.launch {
            bannerList.addAll(wanAndroidRepository.getBanner().data)
        }
    }


    fun loadArticle() {
        viewModelScope.launch {
            val responseData = wanAndroidRepository.getArticle(curPage)
            curPage = responseData.data.curPage
            articleList.addAll(responseData.data.datas)
        }
    }
}