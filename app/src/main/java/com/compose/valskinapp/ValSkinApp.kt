package com.compose.valskinapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.valskinapp.ui.component.BottomBar
import com.compose.valskinapp.ui.navigation.Screen
import com.compose.valskinapp.ui.screen.detail.DetailApp
import com.compose.valskinapp.ui.screen.favorite.FavoriteApp
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
                        navController.navigate(Screen.Detail.createRoute(skinId))
                    }
                )
            }
            composable(
                route = Screen.Detail.route
            ) {
                val id = it.arguments?.getString("skinId") ?: "1"
                DetailApp(idSkin = id)
            }
            composable(Screen.Favorite.route) {
                FavoriteApp()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
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