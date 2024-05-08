package com.example.navigationdrawer

sealed class Screens(val screen_name: String) {
        data object Home : Screens("Home")
        data object Profile: Screens("Profile")
        data object Settings: Screens( "Settings")
}