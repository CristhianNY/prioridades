package com.cristhianbonilla.features_home.ui.perfil

import android.os.Bundle
import android.view.View
import com.cristhianbonilla.feature_home.BR
import com.cristhianbonilla.feature_home.R
import com.cristhianbonilla.feature_home.databinding.FragmentPreviewMagazineBinding
import com.cristhianbonilla.foundations.base.BaseFragment

class ProfileFragment : BaseFragment<
        ProfileState,
        ProfileData,
        ProfileTracker,
        ProfileViewModel,
        FragmentPreviewMagazineBinding>(R.layout.profile_dashboard, BR.viewModel, BR.data) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getUserInformation()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        viewModel.getUserInformation()
        super.onResume()

    }
}