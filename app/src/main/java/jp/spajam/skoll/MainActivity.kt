package jp.spajam.skoll

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import jp.spajam.skoll.ui.navigation.NavGraph
import jp.spajam.skoll.ui.theme.Spajam2024Theme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Spajam2024Theme {
                val navController = rememberNavController()
                NavGraph(
                    navController = navController,
                )
            }
        }
    }
}
