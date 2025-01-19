package com.arshia.lightdocumentreader.core.data.repository

import com.arshia.lightdocumentreader.core.common.Resource
import com.arshia.lightdocumentreader.core.model.Document
import kotlinx.coroutines.flow.Flow

interface DocumentRepository {

    val documents: Flow<List<Document>>

    suspend fun refresh(): Flow<Resource<Nothing>>

}
