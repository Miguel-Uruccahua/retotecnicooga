package com.example.retotecnicooga.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.retotecnicooga.ui.actions.ActionScreen
import com.example.retotecnicooga.ui.details.DetailScreen
import com.example.retotecnicooga.ui.suggestions.SuggestionScreen
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationScreen(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar(navController)
        }
    ) { padding ->
        MainNavigation(navController)
    }
}

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Suggestions ) {
        composable<Actions> {
            ActionScreen()
        }
        composable<Details> {
            DetailScreen()
        }
        composable<Suggestions> {
            SuggestionScreen()
        }
    }
}
