package com.example.whattoeat.ui.theme.screens.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController : NavHostController = rememberNavController()){
    Text(text = "Home")
}