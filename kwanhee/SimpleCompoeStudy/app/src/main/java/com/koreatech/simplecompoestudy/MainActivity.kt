package com.koreatech.simplecompoestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.koreatech.simplecompoestudy.ui.theme.SimpleCompoeStudyTheme
import com.koreatech.simplecompoestudy.utils.showToast
import com.koreatech.simplecompoestudy.view.MainSideEffect
import com.koreatech.simplecompoestudy.view.MainView
import com.koreatech.simplecompoestudy.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.compose.collectSideEffect

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleCompoeStudyTheme {
                Scaffold { paddingValues ->
                    val context = LocalContext.current
                    val viewModel: MainViewModel = hiltViewModel()

                    viewModel.collectSideEffect {
                        when (it) {
                            is MainSideEffect.Toast -> {
                                context.showToast(it.message)
                            }
                        }
                    }

                    MainView(
                        modifier = Modifier.padding(paddingValues),
                        context = context,
                        viewModel = viewModel,
                    )
                }
            }
        }
    }
}
