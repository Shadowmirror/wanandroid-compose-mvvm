package miao.kmirror.wanndroid.compose.utils

import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

inline fun <reified T> T.toPrettyJson(): String where T : Any {
    val json = Json {
        prettyPrint = true
    }
    val serializer = serializer<T>()
    return json.encodeToString(serializer, this)
}