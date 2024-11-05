package miao.kmirror.wanndroid.compose.network

import de.jensklingenberg.ktorfit.http.GET

interface WanAndroidApi {
    @GET("tree/json")
    suspend fun getBanner(): String
}