package com.santansarah.oauthxml

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.santansarah.oauthxml.databinding.ActivityMainBinding
import com.santansarah.oauthxml.ui.main.MainViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by inject<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val uri = intent?.data

        uri?.let {
            val code = it.getQueryParameter("code")
            val state = it.getQueryParameter("state")

            Log.d("test", "GitHub response: $code, $state")
            mainViewModel.validate(code, state)

        }
    }

}