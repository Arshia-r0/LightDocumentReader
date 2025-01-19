package com.arshia.lightdocumentreader.core.model

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arshia.lightdocumentreader.core.database.DocumentDao
import com.arshia.lightdocumentreader.core.model.serializer.UriSerializer
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = DocumentDao.TABLE_NAME)
data class Document(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    @Serializable(with = UriSerializer::class)
    val uri: Uri,
//    val type: FileType,
    val size: Int,
)
