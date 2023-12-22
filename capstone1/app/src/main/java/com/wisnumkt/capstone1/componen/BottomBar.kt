package com.wisnumkt.capstone1.componen

import android.view.MotionEvent
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wisnumkt.capstone1.R
import com.wisnumkt.capstone1.navigation.BottomBarItem
import com.wisnumkt.capstone1.navigation.BottomNavItem
import com.wisnumkt.capstone1.ui.theme.Capstone1Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    currentScreen: BottomNavItem,
    onItemSelected: (BottomNavItem) -> Unit,
) {
    NavigationBar {
        val bottomNavigation = listOf(
            BottomBarItem(
                title = stringResource(id = R.string.txt_home),
                icon = Icons.Outlined.Home,
                screen = BottomNavItem.Home
            ),
            BottomBarItem(
                title = stringResource(id = R.string.txt_maps),
                icon = Icons.Outlined.Place,
                screen = BottomNavItem.Place
            ),
            BottomBarItem(
                title = stringResource(id = R.string.txt_search),
                icon = Icons.Outlined.Search,
                screen = BottomNavItem.Search
            ),
            BottomBarItem(
                title = stringResource(id = R.string.txt_profile),
                icon = Icons.Outlined.Person,
                screen = BottomNavItem.Person
            )
        )
        NavigationBar {
            bottomNavigation.forEach { item ->
                NavigationBarItem(
                    icon = { Icon(item.icon, contentDescription = item.title) },
                    label = { Text(item.title) },
                    selected = item.screen == currentScreen,
                    onClick = { onItemSelected(item.screen) },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    Capstone1Theme {
        BottomBar(
            currentScreen = BottomNavItem.Home,
            onItemSelected = {},
        )
    }
}