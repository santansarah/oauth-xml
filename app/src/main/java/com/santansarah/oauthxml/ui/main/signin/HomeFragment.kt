package com.santansarah.oauthxml.ui.main.signin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.santansarah.oauthxml.R
import com.santansarah.oauthxml.databinding.FragmentHomeBinding
import com.santansarah.oauthxml.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel by activityViewModel<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        //tmp
        findNavController().navigate(R.id.action_navigation_home_to_exploreFragment)

        val btnSignIn = binding.btnSignIn
        btnSignIn.setOnClickListener {
            val authURL = mainViewModel.getAuthURL()
            val browserIntent = getWebAuthorizationIntent(authURL)
            activity?.startActivity(browserIntent)
        }

        mainViewModel.accessToken.observe(viewLifecycleOwner) { accessToken ->
            if (accessToken.isNotEmpty()) {
                findNavController().navigate(R.id.action_navigation_home_to_exploreFragment)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getWebAuthorizationIntent(url: String) =
        Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            addCategory(Intent.CATEGORY_BROWSABLE)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
}