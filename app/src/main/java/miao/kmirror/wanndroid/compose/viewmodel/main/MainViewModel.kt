package miao.kmirror.wanndroid.compose.viewmodel.main

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import miao.kmirror.wanndroid.compose.network.bean.ArticleDTO
import miao.kmirror.wanndroid.compose.network.bean.BannerDTO
import miao.kmirror.wanndroid.compose.repository.WanAndroidRepository
import org.koin.android.annotation.KoinViewModel


@KoinViewModel
class MainViewModel(
    private val wanAndroidRepository: WanAndroidRepository
) : ViewModel() {
    val bannerDTOList = mutableStateListOf<BannerDTO>()
    val articleDTOList = mutableStateListOf<ArticleDTO>()
    private var curPage = 0

    fun initData() {
        loadBanner()
        loadArticle()
    }


    private fun loadBanner() {
        viewModelScope.launch {
            bannerDTOList.addAll(wanAndroidRepository.getBanner().data)
        }
    }


    fun loadArticle() {
        viewModelScope.launch {
            val responseData = wanAndroidRepository.getArticle(curPage)
            curPage = responseData.data.curPage
            articleDTOList.addAll(responseData.data.datas)
        }
    }
}