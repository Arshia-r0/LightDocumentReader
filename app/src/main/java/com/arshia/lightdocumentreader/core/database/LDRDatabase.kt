package com.arshia.lightdocumentreader.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arshia.lightdocumentreader.core.model.Document


@TypeConverters(value = [RoomTypeConverter::class])
@Database(
    entities = [Document::class],
    version = 1,
)
abstract class LDRDatabase : RoomDatabase() {

    companion object {
        const val NAME = "LDR_DB"
    }

    abstract fun documentDao(): DocumentDao

}
