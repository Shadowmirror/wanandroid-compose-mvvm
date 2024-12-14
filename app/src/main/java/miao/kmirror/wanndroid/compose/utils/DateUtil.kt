package miao.kmirror.wanndroid.compose.utils

object TimeUtil {
    fun getSystemTime(): Long {
        return System.currentTimeMillis()
    }


    /**
     * 这里是获取网络时间, 由于暂时没有找到纯 Kotlin 的 NTP 方案库, 所以暂时就先获取系统时间校验
     * */
    fun getNetworkTime(): Long {
        return System.currentTimeMillis()
    }
}