package jp.spajam.skoll.ui.screen.tagChoose

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val TAG_CHOOSE_ROUTE = "tag_choose_route"

fun NavController.goToTagChoose() = navigate(TAG_CHOOSE_ROUTE)
fun NavGraphBuilder.tagChooseScreen(goToMatchScreen: () -> Unit) = composable(TAG_CHOOSE_ROUTE) {
    TagChooseScreen(goToMatchScreen)
}