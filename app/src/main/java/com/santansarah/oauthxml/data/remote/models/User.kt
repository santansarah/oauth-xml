package com.santansarah.oauthxml.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("avatar_url") val avatarUrl: String,
    val bio: String = "",
    val blog: String = "",
    val location: String = "",
    val login: String = "",
    val name: String = "",
    val url: String = ""
)


