/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.CountDownTimer
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

@Composable
fun HomeScreen() {
    var t by remember { mutableStateOf(0L) }
    val timer = object : CountDownTimer(2 * 60 * 60 * 1000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            t = millisUntilFinished
        }

        override fun onFinish() {
            t = 0
        }
    }
    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column {
            Text("The Countdown", fontWeight = FontWeight.Bold, fontSize = 25.sp)
            androidx.compose.foundation.layout.Spacer(
                modifier = androidx.compose.ui.Modifier.height(
                    30.dp
                )
            )
            Surface(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .clipToBounds(),
                color = MaterialTheme.colors.primary
            ) {
                TimeScreen(t)
            }
            Spacer(modifier = Modifier.height(20.dp))
            ActionScreen(
                onReset = {
                    timer.cancel()
                    t = 0
                },
                onStart = {
                    timer.start()
                }
            )
        }
    }
}

private val format = SimpleDateFormat("HHmmss")

@Composable
fun TimeScreen(t: Long) {
    format.timeZone = TimeZone.getTimeZone("GMT")
    val date = format.format(Date(t))
    val zeroPic = getTimerPic(num = date[5].toInt() - 48)
    val onePic = getTimerPic(num = date[4].toInt() - 48)
    val twoPic = getTimerPic(num = date[3].toInt() - 48)
    val threePic = getTimerPic(num = date[2].toInt() - 48)
    val fourPic = getTimerPic(num = date[1].toInt() - 48)
    val fivePic = getTimerPic(num = date[0].toInt() - 48)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(10.dp)
    ) {
        Image(
            painter = fivePic,
            contentDescription = null,
            modifier = Modifier.weight(1f)
        )
        Image(
            painter = fourPic,
            contentDescription = null,
            modifier = Modifier.weight(1f)
        )
        Text(text = ":", fontWeight = FontWeight.Bold, fontSize = 30.sp, color = Color.Black)
        Image(
            painter = threePic,
            contentDescription = null,
            modifier = Modifier.weight(1f)
        )
        Image(
            painter = twoPic,
            contentDescription = null,
            modifier = Modifier.weight(1f)
        )
        Text(text = ":", fontWeight = FontWeight.Bold, fontSize = 30.sp, color = Color.Black)
        Image(
            painter = onePic,
            contentDescription = null,
            modifier = Modifier.weight(1f)
        )
        Image(
            painter = zeroPic,
            contentDescription = null,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun getTimerPic(num: Int): Painter {
    return when (num) {
        0 -> painterResource(id = R.drawable.num0)
        1 -> painterResource(id = R.drawable.num1)
        2 -> painterResource(id = R.drawable.num2)
        3 -> painterResource(id = R.drawable.num3)
        4 -> painterResource(id = R.drawable.num4)
        5 -> painterResource(id = R.drawable.num5)
        6 -> painterResource(id = R.drawable.num6)
        7 -> painterResource(id = R.drawable.num7)
        8 -> painterResource(id = R.drawable.num8)
        9 -> painterResource(id = R.drawable.num9)
        else -> painterResource(id = R.drawable.num0)
    }
}

@Composable
fun ActionScreen(onReset: () -> Unit, onStart: () -> Unit) {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        Button(onClick = { onReset() }, modifier = Modifier.wrapContentWidth(Alignment.Start)) {
            Text(text = "RESET", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.width(20.dp))
        Button(onClick = { onStart() }, modifier = Modifier.wrapContentWidth(Alignment.End)) {
            Text(text = "START", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
    }
}

@Preview
@Composable
fun previewHomeScreen() {
    MyTheme(false) {
        TimeScreen(0)
    }
}
