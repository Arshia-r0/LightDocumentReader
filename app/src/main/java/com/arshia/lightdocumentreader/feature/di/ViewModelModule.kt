package com.arshia.lightdocumentreader.feature.di

import com.arshia.lightdocumentreader.feature.main.MainScreenViewModel
import com.arshia.lightdocumentreader.feature.viewer.ViewerScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    viewModelOf(::MainScreenViewModel)

    viewModelOf(::ViewerScreenViewModel)

}
