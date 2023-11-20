package com.example.dogprofilepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun Video(){
    var isVolumeControlVisible by remember { mutableStateOf(false) }
    var MicMuted by remember { mutableStateOf(false) }
    var VideoClose by remember { mutableStateOf(false) }


    Box {
        Image(
            painter = painterResource(id = R.drawable.girl2),
            contentDescription = "girl2",
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentScale = ContentScale.Crop
        )

        Row {
            Image(painter = painterResource(id = R.drawable.back),
                contentDescription = "back",
                modifier = Modifier
                    .size(20.dp)
                    .offset(30.dp, 30.dp))
            Image(painter = painterResource(id = R.drawable.husky),
                contentDescription = "husky",
                modifier = Modifier
                    .size(120.dp)
                    .offset(260.dp, 30.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .aspectRatio(0.9f)
                    .border(
                        width = 1.5.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentScale = ContentScale.Crop
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .padding(bottom = 24.dp)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 麦克风图标
            NavigationIcon(
                icon = if (MicMuted) painterResource(id = R.drawable.micmute) else painterResource(id = R.drawable.mic),
                label = "Mic",
                onClick = {
                    MicMuted = !MicMuted
                }
            )

            // 音量控制图标
            NavigationIcon(icon = painterResource(id = R.drawable.volume), label = "",
                onClick = {
                    isVolumeControlVisible = true
                })

            // 视频图标
            NavigationIcon(
                icon =if(VideoClose) painterResource(id = R.drawable.videoclose)
                        else painterResource(id = R.drawable.video_solid),
                label = "Video",
                onClick = {
                    VideoClose=!VideoClose
                })

            // 聊天图标
            NavigationIcon(icon = painterResource(id = R.drawable.chat), label = "chat",)

            // 关闭
            NavigationIcon(icon = painterResource(id = R.drawable.x_solid), label = "close")

        }
        if (isVolumeControlVisible) {
            VolumeControlDialog(onClose = { isVolumeControlVisible = false })
        }
    }
}

@Composable
fun NavigationIcon(icon: Painter, label: String, onClick: () -> Unit = {}) {
    val backgroundModifier = if (label == "chat") {
        Modifier.background(Color.Green, shape = CircleShape)
    } else if (label == "close") {
        Modifier.background(Color.Red, shape = CircleShape)
    } else {
        Modifier.background(Color.Gray, shape = CircleShape)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(36.dp)
            .border(1.dp, Color.White, shape = CircleShape)
            .then(backgroundModifier)
            .clickable { onClick.invoke() }
    ) {
        Icon(
            painter = icon,
            contentDescription = label,
            modifier = Modifier.size(20.dp),
            tint = Color.White
        )
    }
    Spacer(modifier = Modifier.height(4.dp))
    //    Text(text = label, color = Color.White)
}

@Composable
fun VolumeControlDialog(onClose: () -> Unit) {
    // 定义音量状态
    var volume by remember { mutableStateOf(0.5f) }

    // 使用 Dialog 显示音量调整的 UI 元素
    Dialog(
        onDismissRequest = { onClose.invoke() },
        content = {

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()) // 保持垂直滚动状态
            ) {
                // 添加你的音量控制 UI 元素
                Spacer(modifier = Modifier.height(20.dp))

                // 添加滑动条用于调整音量
                Slider(
                    value = volume, // 使用状态中的音量值
                    onValueChange = {
                        // 处理音量变化
                        volume = it
                    },
                    modifier = Modifier
                        .padding(vertical = 20.dp) // 调整垂直间距
                        .height(100.dp)
                        .offset(x = (-160).dp)
                        .rotate(270f)
                )
            }
        }
    )
}





@Preview
@Composable
fun VideoView(){
    Video()
}
