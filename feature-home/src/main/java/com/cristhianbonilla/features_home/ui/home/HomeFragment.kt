package com.cristhianbonilla.features_home.ui.home

import android.os.Bundle
import android.view.View
import com.cristhianbonilla.feature_home.R
import com.cristhianbonilla.feature_home.BR
import com.cristhianbonilla.feature_home.databinding.FragmentHomeBinding
import com.cristhianbonilla.foundations.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<
        HomeMagazineState,
        HomeData,
        HomeTracker,
        HomeViewModel,
        FragmentHomeBinding>(R.layout.fragment_home, BR.viewModel, BR.data) {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}