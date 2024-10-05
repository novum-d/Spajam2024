package jp.spajam.skoll.ui.screen.idSearch

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ID_SEARCH_ROUTE = "id_search_route"

fun NavGraphBuilder.idSearchScreen(goToTagChooseScreen: () -> Unit) = composable(ID_SEARCH_ROUTE) {
    IdSearchScreen(goToTagChooseScreen)
}