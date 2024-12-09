package miao.kmirror.wanndroid.compose.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import miao.kmirror.wanndroid.compose.Database
import miao.kmirror.wanndroid.compose.MyApp
import org.koin.core.annotation.Single


@Single
class WanAndroidDbService {
    private val driver: SqlDriver = AndroidSqliteDriver(Database.Schema, MyApp.getInstance(), "WanAndroid.db")

    private val database = Database(driver)

    val userDao by lazy {
        database.userQueries
    }
}