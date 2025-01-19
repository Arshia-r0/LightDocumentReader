package com.arshia.lightdocumentreader.core.data.repository.imp

import com.arshia.lightdocumentreader.core.common.Resource
import com.arshia.lightdocumentreader.core.contentprovider.DocumentProvider
import com.arshia.lightdocumentreader.core.data.repository.DocumentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DocumentRepositoryImpl(
    private val documentProvider: DocumentProvider,
) : DocumentRepository {

    override val documents = flow {

    }

    override suspend fun refresh(): Flow<Resource<Nothing>> = flow {
        try {
            emit(Resource.Loading)
            val documents = documentProvider.getAll()
            
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }
    }

}
