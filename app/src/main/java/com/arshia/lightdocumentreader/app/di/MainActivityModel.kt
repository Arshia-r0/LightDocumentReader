package com.arshia.lightdocumentreader.app.di

import com.arshia.lightdocumentreader.app.MainActivityViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val mainActivityModule = module {

    viewModelOf(::MainActivityViewModel)

}
