package com.cristhianbonilla.features_home.ui.search

import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.foundations.base.BaseViewModel

class SearchViewModel(
    scope: Scope,
    data: SearchData,
    tracker: SearchTracker
) :
    BaseViewModel<SearchState, SearchData, SearchTracker>(
        scope,
        data,
        tracker
    ) {

}