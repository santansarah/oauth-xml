package com.santansarah.oauthxml.data.remote

import android.util.Log
import com.santansarah.oauthxml.data.remote.models.TokenResponse
import com.santansarah.oauthxml.utils.CLIENT_ID
import com.santansarah.oauthxml.utils.CLIENT_SECRET
import com.santansarah.oauthxml.utils.REDIRECT_URI
import com.santansarah.oauthxml.utils.TOKEN_PATH
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.submitForm
import io.ktor.http.Parameters

class OAuthApi(private val client: HttpClient) {

    suspend fun authorize(code: String): TokenResponse? {
        Log.d("test", "code used for api: $code")
        return try {
            val response: TokenResponse = client.submitForm(
                url = TOKEN_PATH,
                formParameters = Parameters.build {
                    append("client_id", CLIENT_ID)
                    append("client_secret", CLIENT_SECRET)
                    append("code", code)
                    append("redirect_uri", REDIRECT_URI)
            }).body()
            Log.d("test", "auth response: $response")
            response
        } catch (apiError: Exception) {
            Log.d("test", apiError.toString())
            null
        }
    }

}