package com.arshia.lightdocumentreader.core.datastore

import androidx.datastore.core.DataStore
import com.arshia.lightdocumentreader.core.designsystem.theme.AppTheme
import kotlinx.coroutines.flow.map

class LDRDataStore(
    private val dataStore: DataStore<LDRDataStoreModel>
) {

    val ldrData = dataStore.data.map {
        LDRDataStoreModel(
            it.appTheme,
        )
    }

    suspend fun setAppTheme(appTheme: AppTheme) = dataStore.updateData {
        it.copy(appTheme = appTheme)
    }

}
