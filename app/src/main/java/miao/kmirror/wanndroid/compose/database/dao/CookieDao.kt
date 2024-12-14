package miao.kmirror.wanndroid.compose.database.dao

import androidx.room.*
import miao.kmirror.wanndroid.compose.database.entity.CookieEntity

@Dao
interface CookieDao {

    // 插入或更新一个 CookieEntity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(cookieEntity: CookieEntity)

    // 根据名称查询一个 CookieEntity
    @Query("SELECT * FROM CookieEntity WHERE name = :name")
    suspend fun getCookieByName(name: String): CookieEntity?

    // 查询所有 CookieEntity
    @Query("SELECT * FROM CookieEntity")
    suspend fun getAllCookies(): List<CookieEntity>

    // 删除一个 CookieEntity
    @Delete
    suspend fun deleteCookie(cookieEntity: CookieEntity)

    // 删除所有 CookieEntity
    @Query("DELETE FROM CookieEntity")
    suspend fun deleteAllCookies()
}