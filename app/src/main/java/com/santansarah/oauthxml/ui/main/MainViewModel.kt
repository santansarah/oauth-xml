package com.santansarah.oauthxml.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santansarah.oauthxml.data.remote.UserApi
import com.santansarah.oauthxml.data.remote.models.UserRepo
import com.santansarah.oauthxml.data.remote.models.User
import com.santansarah.oauthxml.ui.OAuthManager
import kotlinx.coroutines.launch


class MainViewModel(
    private val oAuthManager: OAuthManager,
    private val userApi: UserApi
) : ViewModel() {

    val accessToken = oAuthManager.accessToken
    private var _user: MutableLiveData<User?> = MutableLiveData()

    private var _userRepos: MutableLiveData<List<UserRepo>?> = MutableLiveData()
    val userRepos: LiveData<List<UserRepo>?>
        get() = _userRepos

    fun getUser(): LiveData<User?> {
        if (_user.value == null) {
            getUserInfo()
        }
        return _user
    }

    fun validate(code: String?, state: String?) {
        if (!code.isNullOrBlank() && !state.isNullOrBlank())
            viewModelScope.launch {
                oAuthManager.getToken(code, state)
            }
    }

    private fun getUserInfo() {
        accessToken.value?.let {token ->
            viewModelScope.launch {
                _user.value = userApi.getUser(token)
                _user.value?.let {user ->
                    _userRepos.value = userApi.getUserRepos(
                        token)
                }
            }
        }
    }

    fun getUserRepos() {
        accessToken.value?.let {
            viewModelScope.launch {
                _user.value = userApi.getUser(it)
            }
        }
    }

    fun getAuthURL() = oAuthManager.getAuthURL()

}