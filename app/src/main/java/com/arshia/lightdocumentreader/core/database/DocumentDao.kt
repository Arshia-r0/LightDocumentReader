package com.arshia.lightdocumentreader.core.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.arshia.lightdocumentreader.core.model.Document
import kotlinx.coroutines.flow.Flow

@Dao
interface DocumentDao {
    
    companion object {
        const val TABLE_NAME = "documents"
    }
    
    @Query("SELECT * FROM $TABLE_NAME")
    fun index(): Flow<List<Document>>
    
    @Upsert
    fun upsert(vararg documents: Document)
    
    @Delete
    fun delete(vararg documents: Document)
    
}
