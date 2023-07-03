package com.santansarah.oauthxml.data.remote.models

import com.santansarah.oauthxml.data.remote.NullStringCheck
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer


@Serializable
data class UserRepo(
    val name: String,
    @SerialName("full_name") val fullName: String,
    @Serializable(with = NullStringCheck::class) val description: String
)