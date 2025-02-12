package com.arshia.lightdocumentreader.ui.di

import com.arshia.lightdocumentreader.ui.main.MainScreenViewModel
import com.arshia.lightdocumentreader.ui.viewer.ViewerScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    viewModelOf(::MainScreenViewModel)

    viewModelOf(::ViewerScreenViewModel)

}
