package miao.kmirror.wanndroid.compose.page.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import miao.kmirror.wanndroid.compose.network.bean.ArticleDTO
import miao.kmirror.wanndroid.compose.page.webview.FullScreenWebViewDialog
import miao.kmirror.wanndroid.compose.utils.toAnnotatedString

@Composable
fun CardItem(articleDTO: ArticleDTO = ArticleDTO.getSampleObject()) {
    var showDialog by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                showDialog = true
            }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelLarge,
                    text = articleDTO.author.ifBlank { articleDTO.shareUser }
                )

                Text(
                    style = MaterialTheme.typography.labelMedium,
                    text = articleDTO.niceDate.ifBlank { articleDTO.niceShareDate }
                )

            }
            Text(
                text = articleDTO.title.toAnnotatedString(),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 3.dp)
            )

            Text(
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.labelMedium,
                text = "${articleDTO.superChapterName} / ${articleDTO.chapterName}"
            )
        }
    }
    if (showDialog) {
        FullScreenWebViewDialog(articleDTO.link) {
            showDialog = false
        }
    }
}