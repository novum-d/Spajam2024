package jp.spajam.skoll.ui.screen.match

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import jp.spajam.skoll.ui.theme.Spajam2024Theme

@Composable
fun MatchScreen(
    modifier: Modifier = Modifier,
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .then(modifier),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    Text("match")
}

@Preview
@Composable
private fun MatchScreenPreview() = Spajam2024Theme {
    MatchScreen()
}