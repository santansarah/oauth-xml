package com.santansarah.oauthxml.data.remote

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder


@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = String::class)
object NullStringCheck : KSerializer<String> {
    override fun deserialize(decoder: Decoder): String {
        return try {
            decoder.decodeString()
        } catch (e: Exception) {
            return ""
        }
    }

}
