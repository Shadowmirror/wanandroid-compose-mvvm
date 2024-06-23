package miao.kmirror.wanandroid.http

import miao.kmirror.wanandroid.bean.ApiResponse
import miao.kmirror.wanandroid.bean.Article
import miao.kmirror.wanandroid.bean.Banner
import miao.kmirror.wanandroid.bean.PageResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WanAndroidService {
    @GET("banner/json")
    suspend fun getBanner(): ApiResponse<List<Banner>>

    /** 获取首页/体系详情文章数据 */
    @GET(/* value = */ "article/list/{pageNo}/json")
    suspend fun getArticlePageList(
        @Path("pageNo") pageNo: Int,
        @Query("page_size") pageSize: Int,
        @Query("cid") categoryId: Int? = null
    ): ApiResponse<PageResponse<Article>>
}