package miao.kmirror.wanndroid.compose.database

import androidx.room.Database
import androidx.room.RoomDatabase
import miao.kmirror.wanndroid.compose.database.dao.UserDao
import miao.kmirror.wanndroid.compose.database.entity.User


@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}


