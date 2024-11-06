package miao.kmirror.wanndroid.compose.bean


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageResponse<T>(
    @SerialName("curPage")
    val curPage: Int,
    @SerialName("datas")
    val datas: List<T>,
    @SerialName("offset")
    val offset: Int,
    @SerialName("over")
    val over: Boolean,
    @SerialName("pageCount")
    val pageCount: Int,
    @SerialName("size")
    val size: Int,
    @SerialName("total")
    val total: Int
)