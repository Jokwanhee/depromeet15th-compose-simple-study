package com.koreatech.simplecompoestudy.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.koreatech.simplecompoestudy.component.SearchField
import com.koreatech.simplecompoestudy.ui.theme.SimpleCompoeStudyTheme
import com.koreatech.simplecompoestudy.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleCompoeStudyTheme {
                Scaffold { paddingValues ->
                    Column(
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        val viewModel: MainViewModel = hiltViewModel()
                        val state by viewModel.collectAsState()

                        viewModel.collectSideEffect {
                            when (it) {
                                is MainSideEffect.Toast -> {
                                    it.message
                                }
                            }
                        }

                        viewModel.loadSearchImages("1")

                    }
                }
            }
        }
    }
}
