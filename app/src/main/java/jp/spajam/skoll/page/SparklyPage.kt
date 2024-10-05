package jp.spajam.skoll.page

import androidx.annotation.ColorInt
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.spajam.skoll.R
import jp.spajam.skoll.ui.theme.Spajam2024Theme
import kotlinx.coroutines.delay

val initOvalVisible = List(OvalRes.entries.size) { false }

@Composable
fun SparklyPage(
    hashTags: List<String> = listOf("Snow Bowl", "SQS", "Event Bridge"),
) {
    val (ovalVisible, updateOvalVisible) = remember { mutableStateOf(initOvalVisible) }

    LaunchedEffect(ovalVisible) {
        val i = ovalVisible.indexOfFirst { it.not() }
        val mutList = if (i != -1) {
            ovalVisible.toMutableList().also { it[i] = true }
        } else {
            initOvalVisible.toMutableList()
        }
        delay(200)
        updateOvalVisible(mutList)
    }

    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(R.drawable.sparkly_background),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxSize(),
        )
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(Modifier.weight(1f))

            Box(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center,
            ) {
                ovalVisible.zip(OvalRes.entries.reversed()).forEach { (ovalVisible, ovalRes) ->
                    Oval(ovalVisible, ovalRes)
                }
            }

            Spacer(Modifier.weight(1f))

            LazyColumn(contentPadding = PaddingValues(start = 24.dp, bottom = 24.dp)) {
                items(hashTags) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Switch(
                            false, {}, modifier = Modifier
                                .scale(0.5F)
                                .size(24.dp)
                        )
                        Spacer(Modifier.width(9.dp))
                        Text("# $it")
                    }
                }
            }
        }
    }
}

@Composable
fun Oval(
    ovalVisible: Boolean,
    ovalRes: OvalRes,
) {
    AnimatedVisibility(
        ovalVisible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize(unbounded = true)
                .size(ovalRes.size.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = Color(0xFF008EFF),
                    shape = CircleShape,
                )
                .background(Color(ovalRes.colorRes))
        )
    }
}

enum class OvalRes(
    val size: Int, // dp
    @ColorInt val colorRes: Long,
) {
    Zero(100, 0xFF2196F3),
    One(Zero.size + inc, 0xFF76C2FF),
    Two(One.size + inc, 0xFFB2DDFF),
    Three(Two.size + inc, 0xFFDCF0FF),
}

const val inc = 120

@Preview(showBackground = true)
@Composable
fun SparklyPagePreview() {
    Spajam2024Theme {
        SparklyPage()
    }
}
