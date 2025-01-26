package miao.kmirror.wanndroid.compose.network.bean


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TagDTO(
    @SerialName("name")
    val name: String = "",
    @SerialName("url")
    val url: String = ""
)