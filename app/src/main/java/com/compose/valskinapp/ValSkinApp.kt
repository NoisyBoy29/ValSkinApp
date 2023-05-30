package com.compose.valskinapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.compose.valskinapp.ui.component.BottomBar
import com.compose.valskinapp.ui.navigation.Screen
import com.compose.valskinapp.ui.screen.detail.DetailApp
import com.compose.valskinapp.ui.screen.home.HomeApp
import com.compose.valskinapp.ui.screen.profile.ProfileScreen
import com.compose.valskinapp.ui.theme.ValSkinAppTheme

@Composable
fun ValSkinApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeApp(
                    navtoDetail = { skinId ->
                        navController.navigate(Screen.DetailSkin.createRoute(skinId))
                    }
                )
            }
            composable(
                route = Screen.DetailSkin.route,
                arguments = listOf(navArgument("skinId") { type = NavType.StringType })
            ) { backStackEntry ->
                val skinId = backStackEntry.arguments?.getString("skinId") ?: ""
                DetailApp(idSkin = skinId, navController = navController)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(navController = navController)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ValSkinAppPreview() {
    ValSkinAppTheme {
        ValSkinApp()
    }
}