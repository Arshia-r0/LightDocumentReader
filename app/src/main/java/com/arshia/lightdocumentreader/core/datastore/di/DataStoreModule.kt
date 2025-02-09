package com.arshia.lightdocumentreader.core.datastore.di

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.arshia.lightdocumentreader.core.datastore.LDRDataStore
import com.arshia.lightdocumentreader.core.datastore.LDRDataStoreModel
import com.arshia.lightdocumentreader.core.datastore.LDRDataStoreModelSerializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataStoreModule = module {

    single<DataStore<LDRDataStoreModel>> {
        DataStoreFactory.create(
            serializer = LDRDataStoreModelSerializer(),
            produceFile = { androidContext().dataStoreFile("ldr.pb") },
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
        )
    }

    singleOf(::LDRDataStore)

}
