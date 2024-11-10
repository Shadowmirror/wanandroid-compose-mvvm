package miao.kmirror.wanndroid.compose.page.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import miao.kmirror.wanndroid.compose.bean.Article
import miao.kmirror.wanndroid.compose.utils.toAnnotatedString

@Composable
fun CardItem(article: Article = Article.getSampleObject()) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
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
                    text = article.author.ifBlank { article.shareUser }
                )

                Text(
                    style = MaterialTheme.typography.labelMedium,
                    text = article.niceDate.ifBlank { article.niceShareDate }
                )

            }
            Text(
                text = article.title.toAnnotatedString(),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 3.dp)
            )

            Text(
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.labelMedium,
                text = "${article.superChapterName} / ${article.chapterName}"
            )
        }
    }
}