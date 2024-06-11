package miao.kmirror.wanandroid.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 * 网络工具类
 *
 * @author LTP 2022/4/14
 */
object NetworkUtil {
    /**
     * 网络是否可用
     *
     * @param context Context
     * @return  网络是否可用
     */
    @Suppress("DEPRECATION")
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }


    /**
     * 是否连接Wifi
     *
     * @param context
     * @return boolean
     */
    @Suppress("DEPRECATION", "unused")

    fun isWifi(context: Context): Boolean {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetInfo = connectivityManager.activeNetworkInfo
        return activeNetInfo?.type == ConnectivityManager.TYPE_WIFI
    }
}