package jp.spajam.skoll.ui.screen.idSearch

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.spajam.skoll.ui.composable.TopBar
import jp.spajam.skoll.ui.theme.AppDrawable
import jp.spajam.skoll.ui.theme.AppString
import jp.spajam.skoll.ui.theme.PrimaryBlue
import jp.spajam.skoll.ui.theme.Spajam2024Theme
import jp.spajam.skoll.ui.theme.TextSecondaryGray

@Composable
fun IdSearchScreen(
    goToTagChooseScreen: () -> Unit,
    modifier: Modifier = Modifier,
) = Scaffold(
    modifier = modifier.fillMaxSize(),
    topBar = {
        TopBar(
            title = stringResource(AppString.id_search_title),
            isShowNavigationIcon = false,
        )
    },
) { innerPadding ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var eventId: String by remember { mutableStateOf("") }
        val logoPainter = painterResource(AppDrawable.logo)

        Spacer(modifier = Modifier.height(24.dp))
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .aspectRatio(logoPainter.intrinsicSize.width / logoPainter.intrinsicSize.height),
            painter = logoPainter,
            contentDescription = null
        )
        Spacer(modifier = Modifier.weight(1f))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = eventId,
            onValueChange = { eventId = it },
            placeholder = {
                Text(stringResource(AppString.id_search_event_id))
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryBlue,
                unfocusedPlaceholderColor = TextSecondaryGray,
                focusedPlaceholderColor = TextSecondaryGray,
            ),
        )
        Spacer(Modifier.weight(3f))
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = goToTagChooseScreen,
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
        ) {
            Text(stringResource(AppString.id_search_start))
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview
@Composable
private fun IdSearchScreenPreview() {
    Spajam2024Theme {
        IdSearchScreen(goToTagChooseScreen = {})
    }
}