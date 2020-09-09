package com.cristhianbonilla.features_home.ui.search

import com.cristhianbonilla.feature_home.BR
import com.cristhianbonilla.feature_home.R
import com.cristhianbonilla.feature_home.databinding.FragmentSearchBinding
import com.cristhianbonilla.foundations.base.BaseFragmentDialog

class SearchDialogFragment : BaseFragmentDialog<
        SearchState,
        SearchData,
        SearchTracker,
        SearchViewModel,
        FragmentSearchBinding>(R.layout.fragment_search, BR.viewModel, BR.data)