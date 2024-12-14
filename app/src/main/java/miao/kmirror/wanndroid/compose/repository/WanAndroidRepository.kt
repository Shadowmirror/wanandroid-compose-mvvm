package miao.kmirror.wanndroid.compose.repository

import android.app.Application
import miao.kmirror.wanndroid.compose.bean.ApiResponse
import miao.kmirror.wanndroid.compose.bean.Article
import miao.kmirror.wanndroid.compose.bean.Banner
import miao.kmirror.wanndroid.compose.bean.CoinBean
import miao.kmirror.wanndroid.compose.bean.LoginBean
import miao.kmirror.wanndroid.compose.bean.PageResponse
import miao.kmirror.wanndroid.compose.bean.TreeBean
import miao.kmirror.wanndroid.compose.database.WanAndroidDbService
import miao.kmirror.wanndroid.compose.network.WanAndroidApiService
import org.koin.core.annotation.Single


@Single
class WanAndroidRepository(
    private val wanAndroidApiService: WanAndroidApiService,
    private val wanAndroidDbService: WanAndroidDbService,
    private val application: Application
) {
    suspend fun getBanner(): ApiResponse<List<Banner>> {
        return wanAndroidApiService.wanAndroidApi.getBanner()
    }


    suspend fun getArticle(curPage: Int = 0, cid: Int? = null): ApiResponse<PageResponse<Article>> {
        return wanAndroidApiService.wanAndroidApi.getArticle(curPage, cid)
    }

    suspend fun getTreeBean(): ApiResponse<List<TreeBean>> {
        return wanAndroidApiService.wanAndroidApi.getTreeBean()
    }

    suspend fun login(username: String, password: String): ApiResponse<LoginBean> {
        return wanAndroidApiService.wanAndroidApi.login(username, password)
    }

    suspend fun getCoin(): ApiResponse<CoinBean> {
        return wanAndroidApiService.wanAndroidApi.getCoin()
    }
}