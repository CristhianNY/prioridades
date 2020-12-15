package com.cristhianbonilla.revistaprioridades.di.presentation.search

import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.features_home.HomeActivity
import com.cristhianbonilla.features_home.ui.search.SearchData
import com.cristhianbonilla.features_home.ui.search.SearchDialogFragment
import com.cristhianbonilla.features_home.ui.search.SearchTracker
import com.cristhianbonilla.features_home.ui.search.SearchViewModel
import com.cristhianbonilla.foundations.scope.IOScope
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val searchModule = module {
    scope(named<HomeActivity>()) {
    }

    scope(named<SearchDialogFragment>()) {
        scoped<Scope> { IOScope() }
        viewModel {
            SearchViewModel(
                scope = get(),
                data = SearchData(),
                tracker = get()
            )
        }
        scoped { SearchTracker() }
    }

}