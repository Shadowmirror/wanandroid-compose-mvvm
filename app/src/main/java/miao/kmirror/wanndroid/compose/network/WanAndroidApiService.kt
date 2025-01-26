package miao.kmirror.wanndroid.compose.network

import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.AcceptAllCookiesStorage
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single


@Single
class WanAndroidApiService() {
    private val ktorfit = Ktorfit.Builder()
        .baseUrl(NetworkConfig.BaseUrl)
        .httpClient(HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json { isLenient = true; ignoreUnknownKeys = true })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 10000
                connectTimeoutMillis = 10000
                socketTimeoutMillis = 10000
            }
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.ALL
            }

            install(HttpCookies) {
                // 配置 Cookie 存储
                storage = AcceptAllCookiesStorage()
            }
        })
//        .converterFactories(
//            FlowConverterFactory(),
//            CallConverterFactory(),
//            ResponseConverterFactory()
//        )
        .build()


    val wanAndroidApi by lazy {
        ktorfit.createWanAndroidApi()
    }


}