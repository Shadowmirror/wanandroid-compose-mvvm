package miao.kmirror.wanndroid.compose.network

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import miao.kmirror.wanndroid.compose.bean.ApiResponse
import miao.kmirror.wanndroid.compose.bean.Article
import miao.kmirror.wanndroid.compose.bean.Banner
import miao.kmirror.wanndroid.compose.bean.PageResponse

interface WanAndroidApi {
    @GET("banner/json")
    suspend fun getBanner(): ApiResponse<List<Banner>>

    @GET("article/list/{curPage}/json")
    suspend fun getArticle(@Path("curPage") curPage: Int = 0): ApiResponse<PageResponse<Article>>

}