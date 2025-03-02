package miao.kmirror.wanndroid.compose.network.bean


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoDTO(
    @SerialName("admin")
    val admin: Boolean,
    @SerialName("chapterTops")
    val chapterTops: List<String>,
    @SerialName("coinCount")
    val coinCount: Int,
    @SerialName("collectIds")
    val collectIds: List<Int>,
    @SerialName("email")
    val email: String,
    @SerialName("icon")
    val icon: String,
    @SerialName("id")
    val id: Long,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("password")
    val password: String,
    @SerialName("publicName")
    val publicName: String,
    @SerialName("token")
    val token: String,
    @SerialName("type")
    val type: Int,
    @SerialName("username")
    val username: String
)