package com.arshia.lightdocumentreader.core.model.serializer

import android.net.Uri
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class UriSerializer : KSerializer<Uri> {

    override val descriptor = PrimitiveSerialDescriptor("LDR.UriAsString", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Uri) =
        encoder.encodeString(value.toString())

    override fun deserialize(decoder: Decoder): Uri =
        Uri.parse(decoder.decodeString())

}
