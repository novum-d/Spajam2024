package jp.spajam.skoll.ui.screen.match

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.zIndex
import jp.spajam.skoll.KeywordData
import jp.spajam.skoll.KeywordItem
import jp.spajam.skoll.R
import jp.spajam.skoll.ui.theme.Spajam2024Theme
import kotlinx.coroutines.delay

val quicklyOvalVisible: List<Boolean?> = List(OvalRes.entries.size) { false }
val normalOvalVisible: List<Boolean?> = List(OvalRes.entries.size) { if (it < 1) null else false }
val slowlyOvalVisible = List(OvalRes.entries.size) { if (it < 2) null else false }
val noActionOvalVisible = List(OvalRes.entries.size) { null }

@Composable
fun MatchScreen(
    popBack: () -> Unit,
    hashTags: List<KeywordItem> = listOf(
        KeywordData.keywordData1,
        KeywordData.keywordData2,
        KeywordData.keywordData3,
    ),
) {
    val (ovalVisible, updateOvalVisible) = remember { mutableStateOf(slowlyOvalVisible) }
    val (hashTagFlags, updateHashTagFlags) = remember { mutableStateOf(List(hashTags.size) { true }) }

    LaunchedEffect(ovalVisible) {
        val hashTagFlagSize = hashTagFlags.filter { it }.size

        val i = ovalVisible.indexOfFirst { it == false }
        val mutList = if (i != -1) {
            ovalVisible.toMutableList().also { it[i] = true }
        } else {
            when (hashTagFlagSize) {
                0 -> noActionOvalVisible
                1 -> quicklyOvalVisible
                2 -> normalOvalVisible
                else -> slowlyOvalVisible
            }
        }

        val delayMills = when (hashTagFlagSize) {
            0 -> 50
            1 -> 100
            2 -> 300
            else -> 500
        }
        delay(delayMills.toLong())
        updateOvalVisible(mutList)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.sparkly_background),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxSize(),
        )

        IconButton(
            onClick = popBack,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 4.dp, top = 32.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
            )
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(Modifier.weight(0.35f))

            Box(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center,
            ) {
                ovalVisible.zip(OvalRes.entries).forEach { (ovalVisible, ovalRes) ->
                    ovalVisible?.let {
                        Oval(it, ovalRes)
                    }
                }
            }

            Spacer(Modifier.weight(0.65f))
        }

        LazyColumn(
            contentPadding = PaddingValues(start = 24.dp, bottom = 24.dp),
            modifier = Modifier.align(Alignment.BottomStart),
        ) {
            itemsIndexed(items = hashTags.zip(hashTagFlags)) { i, (item, checked) ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Switch(
                        checked = checked,
                        onCheckedChange = {
                            val mutList =
                                hashTagFlags.toMutableList().also { it[i] = checked.not() }
                            updateHashTagFlags(mutList)

                            if (ovalVisible.filterNotNull()
                                    .isEmpty() && hashTagFlags.any { true }
                            ) {
                                updateOvalVisible(quicklyOvalVisible)
                            }
                        },
                        modifier = Modifier
                            .scale(0.5F)
                            .size(40.dp)
                    )
                    Spacer(Modifier.width(9.dp))
                    Text(item.keyword)
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
        modifier = Modifier
            .size(OvalRes.Three.size.dp)
            .zIndex(-(ovalRes.ordinal).toFloat())
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
fun MatchScreenPreview() {
    Spajam2024Theme {
        MatchScreen({})
    }
}


// @Composable
// fun MatchScreen(
//     modifier: Modifier = Modifier,
// ) = Column(
//     modifier = Modifier
//         .fillMaxSize()
//         .then(modifier),
//     verticalArrangement = Arrangement.Center,
//     horizontalAlignment = Alignment.CenterHorizontally,
// ) {
// }
