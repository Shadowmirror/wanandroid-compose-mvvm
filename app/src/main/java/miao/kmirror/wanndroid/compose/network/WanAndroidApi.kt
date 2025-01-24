package miao.kmirror.wanndroid.compose.network

import de.jensklingenberg.ktorfit.http.Field
import de.jensklingenberg.ktorfit.http.FormUrlEncoded
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import miao.kmirror.wanndroid.compose.bean.ApiResponse
import miao.kmirror.wanndroid.compose.bean.Article
import miao.kmirror.wanndroid.compose.bean.Banner
import miao.kmirror.wanndroid.compose.bean.CoinBean
import miao.kmirror.wanndroid.compose.bean.LoginBean
import miao.kmirror.wanndroid.compose.bean.PageResponse
import miao.kmirror.wanndroid.compose.bean.TreeBean

interface WanAndroidApi {
    @GET("banner/json")
    suspend fun getBanner(): ApiResponse<List<Banner>>

    @GET("article/list/{curPage}/json")
    suspend fun getArticle(
        @Path("curPage") curPage: Int = 0,
        @Query("cid") cid: Int? = null
    ): ApiResponse<PageResponse<Article>>

    @GET("tree/json")
    suspend fun getTreeBean(): ApiResponse<List<TreeBean>>


    /**
     * 登录
     * */
    @POST("user/login")
    @FormUrlEncoded
    suspend fun login(@Field("username") username: String, @Field("password") password: String): ApiResponse<LoginBean?>

    /**
     * 获取积分
     */
    @GET("lg/coin/userinfo/json")
    suspend fun getCoin(): ApiResponse<CoinBean>
}