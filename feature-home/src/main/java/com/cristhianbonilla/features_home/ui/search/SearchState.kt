package com.cristhianbonilla.features_home.ui.search

import com.cristhianbonilla.features_home.HomeState

sealed class SearchState : HomeState() {
    object SearchRequest : SearchState()
}