package com.cristhianbonilla.features_home.ui.details

import com.cristhianbonilla.features_home.HomeState

sealed class PreviewMagazineState : HomeState() {
    object Error : PreviewMagazineState()
    object Loading : PreviewMagazineState()
    object Success : PreviewMagazineState()
    object GoBack : PreviewMagazineState()
    object SubscriptionNotActivated : PreviewMagazineState()
    object SessionExpired : PreviewMagazineState()
    data class NavigateToMagazineReader(var pdfMagazineUrl:String) : PreviewMagazineState()
}