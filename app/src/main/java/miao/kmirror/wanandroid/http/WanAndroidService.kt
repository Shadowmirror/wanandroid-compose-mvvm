package miao.kmirror.wanandroid.http

import miao.kmirror.wanandroid.bean.ApiResponse
import miao.kmirror.wanandroid.bean.Banner
import retrofit2.Response
import retrofit2.http.GET

interface WanAndroidService {
    @GET("banner/json")
    suspend fun getBanner(): ApiResponse<List<Banner>>
}