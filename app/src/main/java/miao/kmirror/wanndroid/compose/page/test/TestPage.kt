package miao.kmirror.wanndroid.compose.page.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import miao.kmirror.wanndroid.compose.viewmodel.test.TestViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TestPage(
    navHostController: NavHostController,
    testViewModel: TestViewModel = koinViewModel()
) {

    val userList by remember { derivedStateOf { testViewModel.userList } }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Button(onClick = {
            testViewModel.queryAllUsers()
        }) {
            Text("查询所有")
        }

        Button(onClick = {
            testViewModel.addRandomUser()
        }) {
            Text("添加一个")
        }

        Button(onClick = {
            testViewModel.deleteLastUser()
        }) {
            Text("删除最后一个")
        }

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            itemsIndexed(userList) { index, item ->
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {

                    Text(
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.labelLarge,
                        text = item.userName,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                }
            }

        }


    }
}