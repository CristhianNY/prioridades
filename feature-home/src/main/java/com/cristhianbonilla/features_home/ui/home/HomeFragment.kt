package com.cristhianbonilla.features_home.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.cristhianbonilla.domain.model.home.MagazineModelItem
import com.cristhianbonilla.feature_home.BR
import com.cristhianbonilla.feature_home.R
import com.cristhianbonilla.feature_home.databinding.FragmentHomeBinding
import com.cristhianbonilla.features_home.ui.details.MagazineDetailsFragmentArgs
import com.cristhianbonilla.foundations.base.BaseFragment

class HomeFragment : BaseFragment<
        HomeMagazineState,
        HomeData,
        HomeTracker,
        HomeViewModel,
        FragmentHomeBinding>(R.layout.fragment_home, BR.viewModel, BR.data) {

    private val args: HomeFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel.getKeyWord()

        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("magazine_list", viewModel.magazineArrayList)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val year = args.year
        val keyWord = args.keyWord
        if (savedInstanceState == null) {
            year?.let { keyWord?.let { it1 -> viewModel.getMagazineList(it, it1) } }
        } else {
            savedInstanceState?.let { inState ->
                (inState["magazine_list"] as ArrayList<MagazineModelItem>)?.let { magazineList ->
                    viewModel.savedMagazineList(
                        magazineList
                    )
                }
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }
}