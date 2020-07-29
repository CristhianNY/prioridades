package com.cristhianbonilla.prioridades.di.presentation.home

import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.features_home.HomeActivity
import com.cristhianbonilla.features_home.ui.home.HomeData
import com.cristhianbonilla.features_home.ui.home.HomeFragment
import com.cristhianbonilla.features_home.ui.home.HomeTracker
import com.cristhianbonilla.features_home.ui.home.HomeViewModel
import com.cristhianbonilla.foundations.scope.IOScope
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


internal val homeModule = module {
    scope(named<HomeActivity>()) {
    }

    scope(named<HomeFragment>()) {
        scoped<Scope> { IOScope() }
        viewModel {
            HomeViewModel(
                scope = get(),
                data = HomeData(),
                tracker = get(),
                getKeyWordsUseCase = get()
            )
        }
        scoped { HomeTracker() }
    }

}