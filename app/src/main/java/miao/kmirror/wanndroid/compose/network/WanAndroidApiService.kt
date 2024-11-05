package miao.kmirror.wanndroid.compose.network

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.CallConverterFactory
import de.jensklingenberg.ktorfit.converter.FlowConverterFactory
import de.jensklingenberg.ktorfit.converter.ResponseConverterFactory
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object WanAndroidApiService {

    private val ktorfit = Ktorfit.Builder()
        .baseUrl(NetworkConfig.BaseUrl)
        .httpClient(HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json { isLenient = true; ignoreUnknownKeys = true })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 10000 // 10秒
                connectTimeoutMillis = 5000  // 5秒
                socketTimeoutMillis = 5000   // 5秒
            }
        })
        .converterFactories(
            FlowConverterFactory(),
            CallConverterFactory(),
            ResponseConverterFactory()
        )
        .build()

    fun getWanAndroidApi(): WanAndroidApi {
        return ktorfit.createWanAndroidApi()
    }
}