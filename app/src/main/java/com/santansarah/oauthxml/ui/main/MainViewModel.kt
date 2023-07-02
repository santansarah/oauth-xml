package com.santansarah.oauthxml.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santansarah.oauthxml.ui.OAuthManager
import kotlinx.coroutines.launch

class MainViewModel(
    private val oAuthManager: OAuthManager
) : ViewModel() {

    val accessToken = oAuthManager.accessToken

    fun validate(code: String?, state: String?) {
        if (!code.isNullOrBlank() && !state.isNullOrBlank())
            viewModelScope.launch {
                oAuthManager.getToken(code, state)
            }
    }

    fun getAuthURL() = oAuthManager.getAuthURL()

}