package com.wisnumkt.capstone1.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomNavItem(val label: String, val icon: ImageVector, val screenRoute: String) {
    Home("Home", Icons.Default.Home, "home"),
    Place("Maps", Icons.Default.Place, "maps"),
    Search("Chat", Icons.Default.Search, "search"),
    Person("Profile", Icons.Default.Person, "profile")
}
