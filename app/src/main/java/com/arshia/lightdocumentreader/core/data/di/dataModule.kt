package com.arshia.lightdocumentreader.core.data.di

import com.arshia.lightdocumentreader.core.data.repository.LDRDataRepository
import com.arshia.lightdocumentreader.core.data.repository.LDRDataRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {

    singleOf(::LDRDataRepositoryImpl) {
        bind<LDRDataRepository>()
    }

}
