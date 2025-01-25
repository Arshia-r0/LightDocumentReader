package com.arshia.lightdocumentreader.app.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType

object LDRNavTypes {

    val uriType = object : NavType<Uri>(
        isNullableAllowed = false,
    ) {

        override fun get(bundle: Bundle, key: String): Uri? = Uri.parse(bundle.getString(key))

        override fun parseValue(value: String): Uri = Uri.parse(value)

        override fun put(bundle: Bundle, key: String, value: Uri) {
            bundle.putString(key, value.toString())
        }

        override fun serializeAsValue(value: Uri): String = value.toString()

    }
}
