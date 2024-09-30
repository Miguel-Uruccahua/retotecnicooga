package com.example.retotecnicooga.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavigationBar(
    navController: NavHostController
){
    val items = listOf(
        NavigationItem.ActionsRoute,
        NavigationItem.DetailsRoute,
        NavigationItem.SuggestionsRoute
    )
    BottomAppBar{
        NavigationBar {
            items.forEach { item->
                val selected = currentRoute(navHostController = navController) == item.route
                NavigationBarItem(
                    selected = selected,
                    onClick = { navController.navigate(item.route) },
                    icon = { Icon(imageVector = item.icon, contentDescription = item.title ) },
                    label = { Text(text = item.title) }
                )
            }
        }
    }
}

sealed class NavigationItem<T>(
    val route:T,
    val title:String,
    val icon: ImageVector
){
    object ActionsRoute : NavigationItem<Actions>(Actions,"Actions", Icons.Default.Build)
    object DetailsRoute : NavigationItem<Details>(Details,"Details", Icons.Default.List)
    object SuggestionsRoute : NavigationItem<Suggestions>(Suggestions,"Suggestions", Icons.Default.Star)
}

@Composable
fun currentRoute(navHostController: NavHostController):String?=
    navHostController.currentBackStackEntryAsState().value?.destination?.route