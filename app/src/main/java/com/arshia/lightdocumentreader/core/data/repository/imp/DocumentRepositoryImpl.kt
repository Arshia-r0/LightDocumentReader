package com.arshia.lightdocumentreader.core.data.repository.imp

import com.arshia.lightdocumentreader.core.common.Resource
import com.arshia.lightdocumentreader.core.contentprovider.DocumentProvider
import com.arshia.lightdocumentreader.core.data.repository.DocumentRepository
import com.arshia.lightdocumentreader.core.database.LDRDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DocumentRepositoryImpl(
    private val documentProvider: DocumentProvider,
    database: LDRDatabase,
) : DocumentRepository {

    private val dao = database.documentDao()

    override val documents = dao.index()

    override suspend fun refresh(): Flow<Resource<Nothing>> = flow {
        try {
            emit(Resource.Loading)
            val documents = documentProvider.getAll()
            println(documents)
            dao.upsert(*documents.toTypedArray())
            emit(Resource.Success())
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }
    }

}
