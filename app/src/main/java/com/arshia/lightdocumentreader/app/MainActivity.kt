package com.arshia.lightdocumentreader.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.arshia.lightdocumentreader.app.navigation.LDRNavHost
import com.arshia.lightdocumentreader.core.designsystem.theme.LightDocumentReaderTheme
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val viewModel by inject<MainActivityViewModel>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var uiState: MainActivityUiState by mutableStateOf(MainActivityUiState.Loading)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState = it }
            }
        }

        splashScreen.setKeepOnScreenCondition {
            uiState == MainActivityUiState.Loading
        }
        
        enableEdgeToEdge()
        setContent {
            LightDocumentReaderTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { ip ->
                    LDRNavHost(ip)
                }
            }
        }
    }
}
