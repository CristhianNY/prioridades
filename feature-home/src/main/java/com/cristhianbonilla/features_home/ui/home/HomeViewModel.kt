package com.cristhianbonilla.features_home.ui.home

import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.domain.usecase.keywords.GetKeyWordsUseCase
import com.cristhianbonilla.foundations.base.BaseViewModel

class HomeViewModel(
    scope: Scope,
    data: HomeData,
    tracker: HomeTracker,
    private val getKeyWordsUseCase: GetKeyWordsUseCase
) : BaseViewModel<HomeMagazineState, HomeData, HomeTracker>(scope, data, tracker) {


}