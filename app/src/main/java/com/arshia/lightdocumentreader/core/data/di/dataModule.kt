package com.arshia.lightdocumentreader.core.data.di

import com.arshia.lightdocumentreader.core.data.repository.DocumentRepository
import com.arshia.lightdocumentreader.core.data.repository.LDRDataRepository
import com.arshia.lightdocumentreader.core.data.repository.PermissionManager
import com.arshia.lightdocumentreader.core.data.repository.imp.DocumentRepositoryImpl
import com.arshia.lightdocumentreader.core.data.repository.imp.LDRDataRepositoryImpl
import com.arshia.lightdocumentreader.core.data.repository.imp.LDRPermissionManager
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {

    singleOf(::LDRDataRepositoryImpl) {
        bind<LDRDataRepository>()
    }

    singleOf(::DocumentRepositoryImpl) {
        bind<DocumentRepository>()
    }

    singleOf(::LDRPermissionManager) {
        bind<PermissionManager>()
    }

}
