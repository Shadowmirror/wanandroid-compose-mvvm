package miao.kmirror.wanndroid.compose.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) var userId: Long = 0,
    var userName: String,
    var databaseId: String
)