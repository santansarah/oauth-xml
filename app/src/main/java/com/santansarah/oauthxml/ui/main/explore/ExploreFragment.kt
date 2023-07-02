package com.santansarah.oauthxml.ui.main.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.google.android.material.carousel.CarouselLayoutManager
import com.santansarah.oauthxml.R
import com.santansarah.oauthxml.data.remote.models.UserRepos
import com.santansarah.oauthxml.databinding.FragmentExploreBinding
import com.santansarah.oauthxml.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ExploreFragment : Fragment(R.layout.fragment_explore) {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel by activityViewModel<MainViewModel>()

    private val testRepos = listOf(
        UserRepos("BLE Scanner", "ble-scanner"),
        UserRepos("Food Tracker", "food-tracker")
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentExploreBinding.bind(view)

        val carouselAdapter = CarouselAdapter(testRepos)
        binding.carouselRecyclerView.adapter = carouselAdapter
        binding.carouselRecyclerView.layoutManager = CarouselLayoutManager()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}