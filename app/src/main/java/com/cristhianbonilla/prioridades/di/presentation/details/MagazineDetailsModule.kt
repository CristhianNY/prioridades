package com.cristhianbonilla.prioridades.di.presentation.details

import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.features_home.HomeActivity
import com.cristhianbonilla.features_home.ui.details.MagazineDetailsFragment
import com.cristhianbonilla.features_home.ui.details.PreviewMagazineData
import com.cristhianbonilla.features_home.ui.details.PreviewMagazineTracker
import com.cristhianbonilla.features_home.ui.details.PreviewMagazineViewModel
import com.cristhianbonilla.foundations.scope.IOScope
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val magazineDetailsModule = module {
    scope(named<HomeActivity>()) {
    }

    scope(named<MagazineDetailsFragment>()) {
        scoped<Scope> { IOScope() }
        viewModel {
            PreviewMagazineViewModel(
                scope = get(),
                data = PreviewMagazineData(),
                tracker = get(),
                getKeyWordsUseCase = get(),
                getMagazineListUseCase = get(),
                getMagazinePdfUseCase = get()
            )
        }
        scoped { PreviewMagazineTracker() }
    }

}