package com.santansarah.oauthxml.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
{
    "access_token" : "29ed478ab86c07f1c069b1af76088f7431396b7c4a2523d06911345da82224a0",
    "token_type" : "bearer",
    "scope" : "public upload"
}*/

@Serializable
data class TokenResponse(
    @SerialName("access_token") val accessToken: String,
    @SerialName("token_type") val tokenType: String,
    val scope: String
)
