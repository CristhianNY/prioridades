package com.cristhianbonilla.features_home.ui.details

import com.cristhianbonilla.features_home.PreMagazineState

sealed class PreviewMagazineState : PreMagazineState() {
    object Error : PreviewMagazineState()
    object Loading : PreviewMagazineState()
    object Success : PreviewMagazineState()
}