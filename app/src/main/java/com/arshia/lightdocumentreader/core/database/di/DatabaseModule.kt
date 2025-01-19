package com.arshia.lightdocumentreader.core.database.di

import androidx.room.Room
import com.arshia.lightdocumentreader.core.database.LDRDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            LDRDatabase::class.java,
            LDRDatabase.NAME,
        ).build()
    }
    
}
