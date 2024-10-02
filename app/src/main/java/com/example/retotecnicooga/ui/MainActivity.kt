package com.example.retotecnicooga.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.retotecnicooga.ui.navigation.NavigationScreen
import com.example.retotecnicooga.ui.theme.RetoTecnicoOGATheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetoTecnicoOGATheme {
                NavigationScreen()
            }
        }
    }
}