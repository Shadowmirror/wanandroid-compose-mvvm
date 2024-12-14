package miao.kmirror.wanndroid.compose.bean


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinBean(
    @SerialName("coinCount")
    val coinCount: Int,
    @SerialName("level")
    val level: Int,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("rank")
    val rank: String,
    @SerialName("userId")
    val userId: Int,
    @SerialName("username")
    val username: String
)