package com.arshia.lightdocumentreader.core.contentprovider

import android.content.ContentUris
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import com.arshia.lightdocumentreader.core.model.Document

class DocumentProviderImpl(
    private val context: Context,
) : DocumentProvider {

    override suspend fun getAll(): List<Document> {
        val queryUri = if (Build.VERSION.SDK_INT >= 29)
            MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL)
        else MediaStore.Files.getContentUri("external")
        val projection = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.TITLE,
            MediaStore.Files.FileColumns.SIZE,
            MediaStore.Files.FileColumns.MEDIA_TYPE,
        )
        val selection: String? = null
        val sortOrder: String? = null

        // todo get file type
        val list = context.contentResolver.query(
            queryUri,
            projection,
            selection,
            null,
            sortOrder,
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
            val title = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.TITLE)
            val size = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)

            return@use buildList {
                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val newItem = Document(
                        id = id,
                        title = cursor.getString(title),
                        size = cursor.getInt(size),
                        uri = ContentUris.withAppendedId(queryUri, id),
                    )
                    add(newItem)
                }
            }
        } ?: emptyList()
        return list
    }

}
