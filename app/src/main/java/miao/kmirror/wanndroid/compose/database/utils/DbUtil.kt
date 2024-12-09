package miao.kmirror.wanndroid.compose.database.utils

import ulid.ULID

object DbUtil {
    fun generateULID(): String {
        return ULID.nextULID().toString()
    }
}