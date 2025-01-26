package miao.kmirror.wanndroid.compose.network

import de.jensklingenberg.ktorfit.http.Field
import de.jensklingenberg.ktorfit.http.FormUrlEncoded
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import miao.kmirror.wanndroid.compose.network.bean.ApiResponse
import miao.kmirror.wanndroid.compose.network.bean.ArticleDTO
import miao.kmirror.wanndroid.compose.network.bean.BannerDTO
import miao.kmirror.wanndroid.compose.network.bean.CoinInfoDTO
import miao.kmirror.wanndroid.compose.network.bean.PageDTO
import miao.kmirror.wanndroid.compose.network.bean.TreeDTO
import miao.kmirror.wanndroid.compose.network.bean.UserInfoDTO

interface WanAndroidApi {
    @GET("banner/json")
    suspend fun getBanner(): ApiResponse<List<BannerDTO>>

    @GET("article/list/{curPage}/json")
    suspend fun getArticle(
        @Path("curPage") curPage: Int = 0,
        @Query("cid") cid: Int? = null
    ): ApiResponse<PageDTO<ArticleDTO>>

    @GET("tree/json")
    suspend fun getTreeBean(): ApiResponse<List<TreeDTO>>


    /**
     * 登录
     * */
    @POST("user/login")
    @FormUrlEncoded
    suspend fun login(@Field("username") username: String, @Field("password") password: String): ApiResponse<UserInfoDTO?>

    /**
     * 获取用户信息
     */
    @GET("user/lg/userinfo/json")
    suspend fun getUserInfo(): ApiResponse<UserInfoDTO>


    /**
     * 获取积分
     */
    @GET("lg/coin/userinfo/json")
    suspend fun getCoin(): ApiResponse<CoinInfoDTO>
}