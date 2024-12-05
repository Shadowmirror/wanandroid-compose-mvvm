package miao.kmirror.wanndroid.compose.bean


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    @SerialName("name")
    val name: String = "",
    @SerialName("url")
    val url: String = ""
)