package com.compose.valskinapp.ui.navigation

sealed class Screen(val route: String){
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("Profile")
    object DetailSkin : Screen("home/{skinId}") {
        fun createRoute(skinId: String) = "home/$skinId"
    }
}
