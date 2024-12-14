package miao.kmirror.wanndroid.compose.page.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import miao.kmirror.wanndroid.compose.viewmodel.test.TestViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TestPage(
    navHostController: NavHostController,
    testViewModel: TestViewModel = koinViewModel()
) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Button(onClick = {
            testViewModel.login()
        }) {
            Text("登录")
        }

        Button(onClick = {
            testViewModel.getCoinBean()
        }) {
            Text("查询积分")
        }

        Button(onClick = {
        }) {
            Text("删除最后一个")
        }


    }
}