package com.cristhianbonilla.features_home.ui.home

import com.cristhianbonilla.domain.model.home.MagazineModelItem
import com.cristhianbonilla.features_home.HomeState
import com.cristhianbonilla.features_home.ui.details.PreviewMagazineState

sealed class HomeMagazineState : HomeState() {
    object Error : HomeMagazineState()
    object Loading : HomeMagazineState()
    object Success : HomeMagazineState()
    data class Search(val keyWords: List<String> , val years: List<String>) : HomeMagazineState()
    data class NavigateToMagazineDetails(val item: MagazineModelItem) : HomeMagazineState()
}