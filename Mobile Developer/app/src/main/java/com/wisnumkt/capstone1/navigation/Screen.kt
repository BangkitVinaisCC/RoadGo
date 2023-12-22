package com.wisnumkt.capstone1.navigation

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Login : Screen("login")
    object Regist : Screen("regist")
    object Home : Screen("home")
    object Search : Screen("search")
    object Detail : Screen("home/{detail}")
}