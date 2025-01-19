package com.arshia.lightdocumentreader.core.contentprovider

import com.arshia.lightdocumentreader.core.model.Document

interface DocumentProvider {

    suspend fun getAll(): List<Document>

}
