package com.santansarah.oauthxml.data.remote

import android.util.Log
import com.santansarah.oauthxml.data.remote.models.UserRepo
import com.santansarah.oauthxml.data.remote.models.User
import com.santansarah.oauthxml.utils.REPOS_PATH
import com.santansarah.oauthxml.utils.USER_PATH
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get

class UserApi(private val client: HttpClient) {

    suspend fun getUser(accessToken: String): User? {
        Log.d("test", "code used for api: $accessToken")
        return try {
            val response: User = client.get(USER_PATH) {
                bearerAuth(accessToken)
            }.body()
            Log.d("test", "user response: $response")
            response
        } catch (apiError: Exception) {
            Log.d("test", apiError.toString())
            null
        }
    }

    suspend fun getUserRepos(accessToken: String): List<UserRepo>? {
        return try {
            val response: List<UserRepo> =
                client.get(REPOS_PATH){
                    bearerAuth(accessToken)
                }.body()
            Log.d("test", "user response: $response")
            response
        } catch (apiError: Exception) {
            Log.d("test", apiError.toString())
            null
        }
    }

}