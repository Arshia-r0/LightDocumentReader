package com.arshia.lightdocumentreader.app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.arshia.lightdocumentreader.app.navigation.LDRNavHost
import com.arshia.lightdocumentreader.core.data.repository.PermissionManager
import com.arshia.lightdocumentreader.core.designsystem.theme.LightDocumentReaderTheme
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.KoinAndroidContext

class MainActivity : ComponentActivity() {

    private val viewModel by inject<MainActivityViewModel>()
    private val permissionManager by inject<PermissionManager>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var uiState: MainActivityUiState by mutableStateOf(MainActivityUiState.Loading)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState = it }
            }
        }
       
        Log.d("MainActivity", intent?.data.toString())

        splashScreen.setKeepOnScreenCondition {
            uiState == MainActivityUiState.Loading
        }
        
        enableEdgeToEdge()
        setContent {
            KoinAndroidContext {
                if (uiState is MainActivityUiState.Success) {
                    LightDocumentReaderTheme((uiState as MainActivityUiState.Success).data.appTheme) {
                        LDRNavHost()
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
//        if (permissionManager.checkPermission(LDRPermission.ReadExternalStorage)) {
//            // todo check permission 
//        }
    }
}
