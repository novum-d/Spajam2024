package jp.spajam.skoll.ui.screen.tagChoose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import jp.spajam.skoll.ui.theme.Spajam2024Theme

@Composable
fun TagChooseScreen(
    goToMatchScreen: () -> Unit,
    modifier: Modifier = Modifier,
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .then(modifier),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    Text("タグ選択画面")
    Button(onClick = goToMatchScreen) {
        Text("next")
    }
}

@Preview
@Composable
private fun TagChooseScreenPreview() = Spajam2024Theme {
    TagChooseScreen(goToMatchScreen = {})
}