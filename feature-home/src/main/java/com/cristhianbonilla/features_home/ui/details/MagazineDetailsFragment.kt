package com.cristhianbonilla.features_home.ui.details

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.fragment.navArgs
import com.cristhianbonilla.feature_home.BR
import com.cristhianbonilla.feature_home.R
import com.cristhianbonilla.feature_home.databinding.FragmentPreviewMagazineBinding
import com.cristhianbonilla.foundations.base.BaseFragment
import com.squareup.picasso.Picasso


class MagazineDetailsFragment : BaseFragment<
        PreviewMagazineState,
        PreviewMagazineData,
        PreviewMagazineTracker,
        PreviewMagazineViewModel,
        FragmentPreviewMagazineBinding>(R.layout.fragment_preview_magazine, BR.viewModel, BR.data) {

    private val args: MagazineDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val item = args.magazineModel

        viewModel.setContent(item)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @BindingAdapter("app:showImage")
        @JvmStatic
        fun showImage(imageView: ImageView, url: String) {
            if(url!=""){
                Picasso.get().load(url).into(imageView)
            }

        }
    }
}

