package com.cristhianbonilla.features_home.ui.home

import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.domain.usecase.contries.GetcountryUseCase
import com.cristhianbonilla.foundations.base.BaseViewModel

class HomeViewModel(
    scope: Scope,
    data: HomeData,
    tracker: HomeTracker,
    private val getCountriesUseCase: GetcountryUseCase
) : BaseViewModel<HomeMagazineState, HomeData, HomeTracker>(scope, data, tracker) {


}