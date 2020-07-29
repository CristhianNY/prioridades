package com.cristhianbonilla.features_home.ui.home

import com.cristhianbonilla.features_home.HomeState

sealed class HomeMagazineState : HomeState() {
    object Error : HomeMagazineState()
    object Loading : HomeMagazineState()
    object Success : HomeMagazineState()
}