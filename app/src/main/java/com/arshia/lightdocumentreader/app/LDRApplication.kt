package com.arshia.lightdocumentreader.app

import android.app.Application
import com.arshia.lightdocumentreader.app.di.mainActivityModule
import com.arshia.lightdocumentreader.core.contentprovider.di.contentProviderModule
import com.arshia.lightdocumentreader.core.data.di.dataModule
import com.arshia.lightdocumentreader.core.database.di.databaseModule
import com.arshia.lightdocumentreader.core.datastore.di.dataStoreModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class LDRApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@LDRApplication)
            modules(
                mainActivityModule,
                dataStoreModule,
                dataModule,
                contentProviderModule,
                databaseModule,
            )
        }
    }
}
