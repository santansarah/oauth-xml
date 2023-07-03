package com.santansarah.oauthxml.ui.main.explore

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.HeroCarouselStrategy
import com.santansarah.oauthxml.R
import com.santansarah.oauthxml.databinding.FragmentExploreBinding
import com.santansarah.oauthxml.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class ExploreFragment : Fragment(R.layout.fragment_explore) {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel by activityViewModel<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentExploreBinding.bind(view)

        mainViewModel.getUser().observe(viewLifecycleOwner) { currentUserInfo ->
            if (currentUserInfo != null) {

                binding.apply {

                    Glide.with(this@ExploreFragment)
                        .load(currentUserInfo.avatarUrl)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .into(ivProfileIcon)

                    tvUserName.text = currentUserInfo.name
                    tvLocation.text = currentUserInfo.location

                }

            }
        }

        mainViewModel.userRepos.observe(viewLifecycleOwner) {repoList ->
            val carouselAdapter = repoList?.let { CarouselAdapter(it) }

            val carouselLayoutManager = CarouselLayoutManager(HeroCarouselStrategy())
            val snapHelper: SnapHelper = CarouselSnapHelper()

            binding.carouselRecyclerView.apply {
                adapter = carouselAdapter
                layoutManager = carouselLayoutManager
                snapHelper.attachToRecyclerView(this)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}