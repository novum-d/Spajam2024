package jp.spajam.skoll.ui.screen.match

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val MATCH_ROUTE = "match_route"

fun NavController.goToMatch() = navigate(MATCH_ROUTE)
fun NavGraphBuilder.matchScreen() = composable(MATCH_ROUTE) {
    MatchScreen()
}