package com.santansarah.oauthxml.utils

import com.santansarah.oauthxml.BuildConfig

const val BASE_URL = "https://github.com"
const val AUTH_PATH = "$BASE_URL/login/oauth/authorize"
const val TOKEN_PATH = "$BASE_URL/login/oauth/access_token"
const val CLIENT_ID = BuildConfig.CLIENT_ID
const val CLIENT_SECRET = BuildConfig.CLIENT_SECRET
const val REDIRECT_URI = "mycode://signin"