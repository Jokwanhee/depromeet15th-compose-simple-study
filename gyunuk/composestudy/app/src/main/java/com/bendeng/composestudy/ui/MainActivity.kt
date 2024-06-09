package com.bendeng.composestudy.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.bendeng.composestudy.ui.screen.home.HomeScreen
import com.bendeng.composestudy.ui.screen.home.HomeViewModel
import com.bendeng.composestudy.ui.theme.ComposestudyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposestudyTheme {
                HomeScreen(modifier = Modifier.fillMaxSize(), viewModel = homeViewModel)
            }
        }
    }
}
