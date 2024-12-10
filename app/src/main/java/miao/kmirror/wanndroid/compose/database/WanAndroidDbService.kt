package miao.kmirror.wanndroid.compose.database

import android.app.Application
import androidx.room.Room
import org.koin.core.annotation.Single

@Single
class WanAndroidDbService(myApp: Application) {
    private val db = Room.databaseBuilder<AppDatabase>(
        context = myApp.applicationContext,
        name = myApp.getDatabasePath("WanAndroid.db").absolutePath
    )

    val wanAndroidDb by lazy {
        db.build()
    }
}