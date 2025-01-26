package miao.kmirror.wanndroid.compose.network.bean


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectArticleInfoDTO(
    @SerialName("count")
    val count: Int
)