package jp.spajam.skoll.ui.screen.tagChoose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.spajam.skoll.ui.composable.DropdownMenu
import jp.spajam.skoll.ui.composable.TopBar
import jp.spajam.skoll.ui.theme.AppDrawable
import jp.spajam.skoll.ui.theme.AppString
import jp.spajam.skoll.ui.theme.PrimaryBlue
import jp.spajam.skoll.ui.theme.Spajam2024Theme

data class PickKeywordState(
    val keyword: String = " ",
    val expanded: Boolean = false,
)

private val demoList = listOf(
    "# kotlin",
    "# android",
    "# compose",
    "# swift",
    "# swiftUI",
    "# UIKit",
    "# XCode",
    "# Flutter",
    "# KMP",
)

@Composable
fun TagChooseScreen(
    goToMatchScreen: () -> Unit,
    popBack: () -> Unit,
    modifier: Modifier = Modifier,
) = Scaffold(
    modifier = modifier.fillMaxSize(),
    topBar = {
        TopBar(
            title = stringResource(AppString.tag_choose_title),
            isShowNavigationIcon = true,
            popBack = popBack,
        )
    }
) { innerPadding ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(innerPadding)
            .padding(horizontal = 24.dp)
            .then(modifier),
    ) {
        var firstPickState: PickKeywordState by remember { mutableStateOf(PickKeywordState()) }
        var secondPickState: PickKeywordState by remember { mutableStateOf(PickKeywordState()) }
        var thirdPickState: PickKeywordState by remember { mutableStateOf(PickKeywordState()) }
        val painter = painterResource(AppDrawable.illustration)

        Spacer(modifier = Modifier.height(24.dp))
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .aspectRatio(painter.intrinsicSize.width / painter.intrinsicSize.height),
            painter = painter,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(58.dp))
        Text(
            text = stringResource(AppString.keyword_that_describe_you),
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
        )
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(AppString.keyword1),
            menuHeight = 200.dp,
            keyword = firstPickState.keyword,
            expanded = firstPickState.expanded,
            menuItems = demoList,
            onExpanded = { firstPickState = firstPickState.copy(expanded = it) },
            onKeywordChange = { firstPickState = firstPickState.copy(keyword = it) },
        )
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(AppString.keyword2),
            menuHeight = 170.dp,
            keyword = secondPickState.keyword,
            expanded = secondPickState.expanded,
            menuItems = demoList,
            onExpanded = { secondPickState = secondPickState.copy(expanded = it) },
            onKeywordChange = { secondPickState = secondPickState.copy(keyword = it) },
        )
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(AppString.keyword3),
            menuHeight = 120.dp,
            keyword = thirdPickState.keyword,
            expanded = thirdPickState.expanded,
            menuItems = demoList,
            onExpanded = { thirdPickState = thirdPickState.copy(expanded = it) },
            onKeywordChange = { thirdPickState = thirdPickState.copy(keyword = it) },
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = goToMatchScreen,
            enabled = firstPickState.keyword.isNotBlank() && secondPickState.keyword.isNotBlank() && thirdPickState.keyword.isNotBlank(),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
        ) {
            Text(stringResource(AppString.next))
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview
@Composable
private fun TagChooseScreenPreview() = Spajam2024Theme {
    TagChooseScreen(
        goToMatchScreen = {},
        popBack = {},
    )
}