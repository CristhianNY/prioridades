package com.cristhianbonilla.features_home.ui.perfil

import com.cristhianbonilla.features_home.HomeState

sealed class ProfileState : HomeState() {
    object Error : ProfileState()
    object Loading : ProfileState()
    object Success : ProfileState()
}