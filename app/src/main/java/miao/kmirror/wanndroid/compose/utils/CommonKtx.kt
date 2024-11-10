package miao.kmirror.wanndroid.compose.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.core.text.HtmlCompat
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer


/**
 * 用于打印精美的 Json 数据的
 * */
inline fun <reified T> T.toPrettyJson(): String where T : Any {
    val json = Json {
        prettyPrint = true
    }
    val serializer = serializer<T>()
    return json.encodeToString(serializer, this)
}

/**
 * 用于生成示例对象, 提供给 UI 观察的
 * */
inline fun <reified T> String.toObject(): T {
    return Json.decodeFromString(serializer(), this.trimIndent())
}


/**
 * 这个是为了防止文本内容存在转移字符, 需要特殊转换的
 * */
fun String.toAnnotatedString(): AnnotatedString {
    val spanned = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
    return AnnotatedString(spanned.toString())
}