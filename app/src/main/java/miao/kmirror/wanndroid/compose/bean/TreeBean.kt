package miao.kmirror.wanndroid.compose.bean


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TreeBean(
    @SerialName("articleList")
    val articleList: List<Article>,
    @SerialName("author")
    val author: String,
    @SerialName("children")
    val children: List<TreeBean>,
    @SerialName("courseId")
    val courseId: Int,
    @SerialName("cover")
    val cover: String,
    @SerialName("desc")
    val desc: String,
    @SerialName("id")
    val id: Int,
    @SerialName("lisense")
    val lisense: String,
    @SerialName("lisenseLink")
    val lisenseLink: String,
    @SerialName("name")
    val name: String,
    @SerialName("order")
    val order: Int,
    @SerialName("parentChapterId")
    val parentChapterId: Int,
    @SerialName("type")
    val type: Int,
    @SerialName("userControlSetTop")
    val userControlSetTop: Boolean,
    @SerialName("visible")
    val visible: Int
)