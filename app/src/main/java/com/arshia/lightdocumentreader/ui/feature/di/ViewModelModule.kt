package com.arshia.lightdocumentreader.ui.feature.di

import com.arshia.lightdocumentreader.ui.feature.main.MainScreenViewModel
import com.arshia.lightdocumentreader.ui.feature.viewer.ViewerScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    viewModelOf(::MainScreenViewModel)

    viewModelOf(::ViewerScreenViewModel)

}
