package com.arshia.lightdocumentreader.core.data.repository.imp

import com.arshia.lightdocumentreader.core.data.repository.LDRDataRepository
import com.arshia.lightdocumentreader.core.datastore.LDRDataStore
import com.arshia.lightdocumentreader.core.model.LdrData
import com.arshia.lightdocumentreader.ui.designsystem.theme.AppTheme
import kotlinx.coroutines.flow.map

class LDRDataRepositoryImpl(
    private val dataStore: LDRDataStore,
) : LDRDataRepository {

    override val ldrData = dataStore.ldrData.map {
        LdrData(it.appTheme)
    }

    override suspend fun setAppTheme(appTheme: AppTheme) {
        dataStore.setAppTheme(appTheme)
    }

}
