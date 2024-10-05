package jp.spajam.skoll.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import jp.spajam.skoll.ui.screen.idSearch.ID_SEARCH_ROUTE
import jp.spajam.skoll.ui.screen.idSearch.idSearchScreen
import jp.spajam.skoll.ui.screen.match.goToMatch
import jp.spajam.skoll.ui.screen.match.matchScreen
import jp.spajam.skoll.ui.screen.tagChoose.goToTagChoose
import jp.spajam.skoll.ui.screen.tagChoose.tagChooseScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = ID_SEARCH_ROUTE,
) = NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = startDestination,
) {
    idSearchScreen(navController::goToTagChoose)
    tagChooseScreen(navController::goToMatch)
    matchScreen()
}
