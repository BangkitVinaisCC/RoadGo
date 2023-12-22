package com.wisnumkt.capstone1.navigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wisnumkt.capstone1.Capstone1
import com.wisnumkt.capstone1.core.utils.getUserEmail
import com.wisnumkt.capstone1.ui.home.HomeScreen
import com.wisnumkt.capstone1.ui.login.LoginScreen
import com.wisnumkt.capstone1.ui.profileseller.ProfileSeller
import com.wisnumkt.capstone1.ui.register.RegistScreen
import com.wisnumkt.capstone1.ui.search.Search
import com.wisnumkt.capstone1.ui.viewmodel.AuthViewModel

@Composable
fun MainNavGraph(
    navController: NavController,
    authViewModel: AuthViewModel,
) {
    val context = LocalContext.current
    // Menggunakan remember untuk mengingat email pengguna antar rekomposisi
    val userEmail = remember { getUserEmail(context) }
    val navGraphBuilder: NavGraphBuilder.() -> Unit = {
        composable(Screen.Main.route) {
            Capstone1(navController= navController)
        }
        composable(Screen.Home.route) {
           HomeScreen(navController = navController, authViewModel = authViewModel, navigateToDetail = { detailSeller ->
               navController.navigate(Screen.Detail.route.replace("{detail}", detailSeller))})
        }
        composable(Screen.Login.route) {
            LoginScreen(viewModel = authViewModel, navController = navController)
        }
        composable(Screen.Regist.route) {
            RegistScreen(viewModel = authViewModel, navController = navController)
        }
        composable(Screen.Search.route) {
            Search(navigateToDetail = { detailSeller ->
                navController.navigate(Screen.Detail.route.replace("{detail}", detailSeller))})
        }
        composable(Screen.Detail.route) {
            ProfileSeller()
        }

        // Add other composable destinations as needed
    }

    NavHost(
        navController = navController as NavHostController,
        startDestination = if (userEmail == null) Screen.Login.route else Screen.Main.route,
        builder = navGraphBuilder
    )
}