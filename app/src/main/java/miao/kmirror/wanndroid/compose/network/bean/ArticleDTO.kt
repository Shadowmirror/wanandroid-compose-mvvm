package miao.kmirror.wanndroid.compose.network.bean


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import miao.kmirror.wanndroid.compose.utils.toObject

@Serializable
data class ArticleDTO(
    @SerialName("adminAdd")
    val adminAdd: Boolean = false,

    @SerialName("apkLink")
    val apkLink: String = "",

    @SerialName("audit")
    val audit: Int = 0,

    @SerialName("author")
    val author: String = "",

    @SerialName("canEdit")
    val canEdit: Boolean = false,

    @SerialName("chapterId")
    val chapterId: Int = 0,

    @SerialName("chapterName")
    val chapterName: String, // 不可能为 null

    @SerialName("collect")
    val collect: Boolean = false,

    @SerialName("courseId")
    val courseId: Int = 0,

    @SerialName("desc")
    val desc: String = "",

    @SerialName("descMd")
    val descMd: String = "",

    @SerialName("envelopePic")
    val envelopePic: String = "",

    @SerialName("fresh")
    val fresh: Boolean = false,

    @SerialName("host")
    val host: String = "",

    @SerialName("id")
    val id: Int = 0,

    @SerialName("isAdminAdd")
    val isAdminAdd: Boolean = false,

    @SerialName("link")
    val link: String, // 不可能为 null

    @SerialName("niceDate")
    val niceDate: String = "",

    @SerialName("niceShareDate")
    val niceShareDate: String = "",

    @SerialName("origin")
    val origin: String = "",

    @SerialName("prefix")
    val prefix: String = "",

    @SerialName("projectLink")
    val projectLink: String = "",

    @SerialName("publishTime")
    val publishTime: Long = 0L,

    @SerialName("realSuperChapterId")
    val realSuperChapterId: Int = 0,

    @SerialName("selfVisible")
    val selfVisible: Int = 0,

    @SerialName("shareDate")
    val shareDate: Long? = null,

    @SerialName("shareUser")
    val shareUser: String = "",

    @SerialName("superChapterId")
    val superChapterId: Int = 0,

    @SerialName("superChapterName")
    val superChapterName: String = "",

    @SerialName("tags")
    val tagDTOS: List<TagDTO> = emptyList(),

    @SerialName("title")
    val title: String = "",

    @SerialName("type")
    val type: Int = 0,

    @SerialName("userId")
    val userId: Int = 0,

    @SerialName("visible")
    val visible: Int = 0,

    @SerialName("zan")
    val zan: Int = 0
) {
    companion object {
        private var sampleObject: ArticleDTO? = null
        fun getSampleObject(): ArticleDTO {
            if (sampleObject == null) {
                sampleObject = """
                    {
                        "adminAdd": false,
                        "apkLink": "",
                        "audit": 1,
                        "author": "",
                        "canEdit": false,
                        "chapterId": 502,
                        "chapterName": "自助",
                        "collect": false,
                        "courseId": 13,
                        "desc": "",
                        "descMd": "",
                        "envelopePic": "",
                        "fresh": false,
                        "host": "",
                        "id": 29081,
                        "isAdminAdd": false,
                        "link": "https://blog.csdn.net/qq_40533422/article/details/143236377",
                        "niceDate": "2024-10-25 16:13",
                        "niceShareDate": "2024-10-25 16:13",
                        "origin": "",
                        "prefix": "",
                        "projectLink": "",
                        "publishTime": 1729844022000,
                        "realSuperChapterId": 493,
                        "selfVisible": 0,
                        "shareDate": 1729844022000,
                        "shareUser": "JasonYin",
                        "superChapterId": 494,
                        "superChapterName": "广场Tab",
                        "tags": [],
                        "title": "《探索 HarmonyOS NEXT(5.0)：开启构建模块化项目架构奇幻之旅 &mdash;&mdash; 构建基础特性层》",
                        "type": 0,
                        "userId": 9296,
                        "visible": 1,
                        "zan": 0
                    }
                """.toObject<ArticleDTO>()
            }
            return sampleObject!!
        }
    }
}