package com.mgoudarzi.movieapp.ui.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mgoudarzi.movieapp.data.util.DataStoreManager
import com.mgoudarzi.movieapp.domain.util.ListTitle
import com.mgoudarzi.movieapp.ui.favorite.FavoriteScreen
import com.mgoudarzi.movieapp.ui.home.HomeScreen
import com.mgoudarzi.movieapp.ui.main.components.MainNavigationBar
import com.mgoudarzi.movieapp.ui.profile.ProfileScreen
import com.mgoudarzi.movieapp.ui.search.SearchScreen
import com.mgoudarzi.movieapp.ui.tabs.home.LoginScreen
import com.mgoudarzi.movieapp.ui.util.FavoriteScreen
import com.mgoudarzi.movieapp.ui.util.HomeScreen
import com.mgoudarzi.movieapp.ui.util.ProfileScreen
import com.mgoudarzi.movieapp.ui.util.SearchScreen
import kotlinx.coroutines.launch

enum class TabIndex { HOME, SEARCH, FAVORITE, PROFILE }

@Composable
fun MainScreen(
  navController: NavHostController = rememberNavController(),
  dataStoreManager: DataStoreManager,
  navigateToMovieScreen: (Int) -> Unit,
  navigateToMovieListScreen: (ListTitle) -> Unit,
) {
  val scope = rememberCoroutineScope()
  val sessionId by dataStoreManager.sessionId.collectAsState("")
  var selectedTab by rememberSaveable {
    mutableStateOf(TabIndex.HOME)
  }

  Scaffold(
    modifier = Modifier
      .background(MaterialTheme.colorScheme.background),
    contentWindowInsets = WindowInsets(0.dp),
    bottomBar = {
      MainNavigationBar(
        navigateTo = {
          navController.navigate(it) {
            popUpTo(navController.graph.findStartDestination().id) {
              saveState = true
            }

            launchSingleTop = true
            if (!(it is FavoriteScreen)) {
              restoreState = true
            }
          }
        },
        selectedTab = selectedTab
      )
    }
  ) { scaffoldPadding ->
    NavHost(
      modifier = Modifier
        .padding(scaffoldPadding),
      navController = navController,
      startDestination = HomeScreen,
      enterTransition = { EnterTransition.None },
      exitTransition = { ExitTransition.None }
    ) {
      composable<HomeScreen> {
        selectedTab = TabIndex.HOME
        HomeScreen(
          navigateToMovieScreen = navigateToMovieScreen,
          navigateToMovieListScreen = navigateToMovieListScreen
        )
      }
      composable<SearchScreen> {
        selectedTab = TabIndex.SEARCH
        SearchScreen(
          navigateToMovieScreen = navigateToMovieScreen
        )
      }
      composable<FavoriteScreen> {
        selectedTab = TabIndex.FAVORITE
        if (sessionId.isEmpty()) {
          LoginScreen()
        } else {
          FavoriteScreen(
            navigateToMovieScreen = navigateToMovieScreen
          )
        }
      }
      composable<ProfileScreen> {
        selectedTab = TabIndex.PROFILE
        if (sessionId.isEmpty()) {
          LoginScreen()
        } else {
          ProfileScreen(
            onLogout = {
              scope.launch {
                dataStoreManager.saveSessionId("")
              }
            }
          )
        }
      }
    }
  }
}