package com.arshia.lightdocumentreader.core.datastore

import android.util.Log
import androidx.datastore.core.Serializer
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class LDRDataStoreModelSerializer : Serializer<LDRDataStoreModel> {

    override val defaultValue = LDRDataStoreModel()

    override suspend fun readFrom(input: InputStream): LDRDataStoreModel {
        return try {
            Json.decodeFromString(
                deserializer = LDRDataStoreModel.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: Exception) {
            defaultValue.also {
                Log.e("readUserData", e.localizedMessage ?: "error")
            }
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun writeTo(t: LDRDataStoreModel, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = LDRDataStoreModel.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}
