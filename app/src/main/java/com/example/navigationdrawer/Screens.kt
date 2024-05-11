package com.example.navigationdrawer

sealed class Screens(val screen_name: String) {
        data object Home : Screens("Home")
        data object Profile: Screens("Profile")
        data object Settings: Screens( "Settings")
        data object Home_Bottom: Screens("Home_Bottom")
        data object Profile_Bottom: Screens("Profile_Bottom")
        data object Search_Bottom: Screens("Search_Bottom")
        data object Notifications_Bottom: Screens( "Notifications_Bottom")
        data object  Post_Bottom : Screens("Post_Bottom")
}