package miao.kmirror.wanndroid.compose.network.bean


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TreeDTO(
    @SerialName("articleList")
    val articleDTOList: List<ArticleDTO>,
    @SerialName("author")
    val author: String,
    @SerialName("children")
    val children: List<TreeDTO>,
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