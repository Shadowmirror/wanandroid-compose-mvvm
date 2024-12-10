package miao.kmirror.wanndroid.compose.page.webview

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewFeature

@Composable
fun SampleWebView(url: String) {
    val backgroundColor = MaterialTheme.colorScheme.background.toArgb()
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true // 设置允许 JS
                settings.domStorageEnabled = true  // 启用 DOM 存储
                settings.loadWithOverviewMode = true  // 适应屏幕大小
                settings.useWideViewPort = true  // 启用广泛视图模式

                if (WebViewFeature.isFeatureSupported(WebViewFeature.ALGORITHMIC_DARKENING)) {
                    WebSettingsCompat.setAlgorithmicDarkeningAllowed(this.settings, true);
                }

                // 设置 WebViewClient 以防止外部浏览器打开链接
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                        val urlTemp = request?.url.toString()

                        // 拦截特定的 URL 模式，例如淘宝、京东等
                        if (urlTemp.contains("taobao.com") || urlTemp.contains("jd.com")) {
                            // 处理拦截逻辑，例如显示提示信息或跳转到其他页面
                            Toast.makeText(view?.context, "恶意跳转被拦截", Toast.LENGTH_SHORT).show()
                            return true // 返回 true 表示拦截该 URL
                        }

                        // 允许其他 URL 加载
                        return false
                    }
                }

                // 加载 URL
                loadUrl(url)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun FullScreenWebViewDialog(url: String, onDismissRequest: () -> Unit) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            SampleWebView(url)
            IconButton(
                onClick = onDismissRequest,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                Icon(Icons.Default.Close, contentDescription = "Close")
            }
        }
    }
}