package com.example.retotecnicooga.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


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
            Text(text = "Acciones")
        }
        composable<Details> {
            Text(text = "Detalles")
        }
        composable<Suggestions> {
            Text(text = "Sugerencias")
        }
    }
}
