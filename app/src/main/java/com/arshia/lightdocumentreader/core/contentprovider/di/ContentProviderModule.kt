package com.arshia.lightdocumentreader.core.contentprovider.di

import com.arshia.lightdocumentreader.core.contentprovider.DocumentProvider
import com.arshia.lightdocumentreader.core.contentprovider.DocumentProviderImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val contentProviderModule = module {

    singleOf(::DocumentProviderImpl) {
        bind<DocumentProvider>()
    }

}
