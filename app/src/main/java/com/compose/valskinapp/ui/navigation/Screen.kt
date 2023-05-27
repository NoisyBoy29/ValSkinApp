package com.compose.valskinapp.ui.navigation

sealed class Screen(val route: String){
    object Home : Screen("home")
    object Detail : Screen("home/{skinId}"){
        fun createRoute(skinId: String) = "home/${skinId}"
    }
    object Favorite : Screen("favorite")
    object Profile : Screen("Profile")
}
