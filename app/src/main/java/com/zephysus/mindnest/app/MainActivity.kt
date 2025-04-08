package com.zephysus.mindnest.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.zephysus.mindnest.app.ui.MindnestApp
import com.zephysus.mindnest.app.ui.rememberMindnestAppState
import com.zephysus.mindnest.app.ui.theme.MindnestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val appState = rememberMindnestAppState()

            MindnestTheme {
                MindnestApp(appState)
            }
        }
    }
}
