package com.cristhianbonilla.prioridades.di.presentation.profile

import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.features_home.HomeActivity
import com.cristhianbonilla.features_home.ui.details.PreviewMagazineTracker
import com.cristhianbonilla.features_home.ui.perfil.ProfileData
import com.cristhianbonilla.features_home.ui.perfil.ProfileFragment
import com.cristhianbonilla.features_home.ui.perfil.ProfileTracker
import com.cristhianbonilla.features_home.ui.perfil.ProfileViewModel
import com.cristhianbonilla.foundations.scope.IOScope
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val profilePresentationModule = module {
    scope(named<HomeActivity>()) {
    }

    scope(named<ProfileFragment>()) {
        scoped<Scope> { IOScope() }
        viewModel {
            ProfileViewModel(
                scope = get(),
                data = ProfileData(),
                tracker = get(),
                getUserInformationUseCase = get(),
                doLogoutUseCase = get()
            )
        }
        scoped { ProfileTracker() }
    }
}