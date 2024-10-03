package com.example.retotecnicooga.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.retotecnicooga.ui.navigation.NavigationScreen
import com.example.retotecnicooga.ui.theme.RetoTecnicoOGATheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.insertData()
        setContent {
            RetoTecnicoOGATheme {
                NavigationScreen()
            }
        }
    }
}