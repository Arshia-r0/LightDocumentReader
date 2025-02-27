package com.arshia.lightdocumentreader.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arshia.lightdocumentreader.app.navigation.LdrNavHost
import com.arshia.lightdocumentreader.ui.designsystem.theme.LightDocumentReaderTheme
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.KoinAndroidContext

class MainActivity : ComponentActivity() {

    private val viewModel by inject<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            viewModel.userData.value == null
        }
        
        enableEdgeToEdge()
        setContent {
            val userData by viewModel.userData.collectAsStateWithLifecycle()
            KoinAndroidContext {
                userData?.let {
                    LightDocumentReaderTheme(it.appTheme) {
                        LdrNavHost()
                    }
                }
            }
        }
    }
}
