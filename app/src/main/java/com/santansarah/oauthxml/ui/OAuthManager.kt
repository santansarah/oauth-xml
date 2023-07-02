package com.santansarah.oauthxml.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.santansarah.oauthxml.data.remote.OAuthApi
import com.santansarah.oauthxml.utils.AUTH_PATH
import com.santansarah.oauthxml.utils.CLIENT_ID
import com.santansarah.oauthxml.utils.REDIRECT_URI
import java.util.UUID

class OAuthManager(
    private val oAuthApi: OAuthApi
) {

    private var oAuthState: String? = null

    val accessToken: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getAuthURL(): String {
        oAuthState = UUID.randomUUID().toString()

        val authURL = StringBuilder()
        authURL.apply {
            append("$AUTH_PATH?")
            append("&redirect_uri=$REDIRECT_URI")
            append("&client_id=$CLIENT_ID")
            append("&state=$oAuthState")
        }

        return authURL.toString()

    }

    suspend fun getToken(code: String, state: String) {
        if (state == oAuthState) {
            Log.d("test", "oauth states match")
            val tokenResponse = oAuthApi.authorize(code)
            tokenResponse?.let {
                accessToken.value = it.accessToken
            }
        }

    }

}
