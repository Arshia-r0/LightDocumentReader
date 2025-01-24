package com.arshia.lightdocumentreader.core.document.di

import com.arshia.lightdocumentreader.core.document.PDFBitmapConverter
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val documentModule = module {

    singleOf(::PDFBitmapConverter)

}
