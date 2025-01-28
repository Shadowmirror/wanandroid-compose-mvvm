package miao.kmirror.wanndroid.compose.page.mine

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.EmojiSupportMatch
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import miao.kmirror.wanndroid.compose.database.WanAndroidDbService
import miao.kmirror.wanndroid.compose.R
import miao.kmirror.wanndroid.compose.viewmodel.mine.MineViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MinePage(
    navHostController: NavHostController,
    mineViewModel: MineViewModel = koinViewModel<MineViewModel>()
) {
    val currentUser = mineViewModel.currentUser.collectAsState()
    if (currentUser.value.id == WanAndroidDbService.GUEST_ID) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                var username by remember { mineViewModel.username }
                var password by remember { mineViewModel.password }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)

                ) {
                    TextField(
                        value = username,
                        label = { Text("账号:") },
                        onValueChange = { username = it },
                        textStyle = TextStyle(
                            platformStyle = PlatformTextStyle(
                                emojiSupportMatch = EmojiSupportMatch.None,
                            )
                        ),
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .background(Color.Blue)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)

                ) {
                    TextField(
                        value = password,
                        label = { Text("密码:") },
                        onValueChange = { password = it },
                        visualTransformation = PasswordVisualTransformation(),
                        textStyle = TextStyle(
                            platformStyle = PlatformTextStyle(
                                emojiSupportMatch = EmojiSupportMatch.None,
                            )
                        ),
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .background(Color.Blue)
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            mineViewModel.login()
                        },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("登录")
                    }

                    Spacer(modifier = Modifier.width(30.dp))

                    Button(
                        onClick = {
                            mineViewModel.addUserCustom()
                        },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("注册")
                    }
                }
            }
        }
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Image(
                    contentScale = ContentScale.Crop,
                    painter = painterResource(R.drawable.img),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(CircleShape)
                )
                Text(
                    text = "用户名：${currentUser.value.username}",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "金币数：${currentUser.value.coinCount}",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

        }
    }

}
