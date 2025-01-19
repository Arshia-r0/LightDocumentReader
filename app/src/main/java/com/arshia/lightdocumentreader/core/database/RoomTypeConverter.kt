package com.arshia.lightdocumentreader.core.database

import android.net.Uri
import androidx.room.TypeConverter

class RoomTypeConverter {

    @TypeConverter
    fun convertUriToString(uri: Uri): String =
        uri.toString()

    @TypeConverter
    fun convertStringToUri(string: String): Uri =
        Uri.parse(string)

}
