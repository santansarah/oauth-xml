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

data class ExploreState(
    val loadingUser: Boolean,
    val loadingRepos: Boolean,
    val userInfo: User?,
    val repos: List<UserRepo>?
)

class MainViewModel(
    private val oAuthManager: OAuthManager,
    private val userApi: UserApi
) : ViewModel() {

    val accessToken = oAuthManager.accessToken

    private var _exploreState: MutableLiveData<ExploreState> = MutableLiveData()

    fun exploreState(): LiveData<ExploreState> {
        if (_exploreState.value == null) {
            _exploreState.value = ExploreState(loadingUser = true,
                loadingRepos = true, null, null)
            getUserInfo()
        }
        return _exploreState
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

                val userInfo = userApi.getUser(token)
                _exploreState.value = _exploreState.value?.copy(loadingUser = false, userInfo = userInfo)

                val repos = userApi.getUserRepos(token)
                _exploreState.value = _exploreState.value?.copy(loadingRepos = false, repos = repos)

            }
        }
    }

    fun getAuthURL() = oAuthManager.getAuthURL()

}