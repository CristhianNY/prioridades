package com.cristhianbonilla.prioridades.di

import com.cristhianbonilla.prioridades.di.data.authenticatin.authenticationDataModule
import com.cristhianbonilla.prioridades.di.data.countries.countryDataModule
import com.cristhianbonilla.prioridades.di.data.home.MagazineListDataModule
import com.cristhianbonilla.prioridades.di.data.keywords.keywordsDataModule
import com.cristhianbonilla.prioridades.di.data.local.localDataModule
import com.cristhianbonilla.prioridades.di.data.networkModule
import com.cristhianbonilla.prioridades.di.data.payments.paymentDataModule
import com.cristhianbonilla.prioridades.di.data.profile.profileDataModule
import com.cristhianbonilla.prioridades.di.data.securityModule
import com.cristhianbonilla.prioridades.di.domain.authentication.authenticationDomainModule
import com.cristhianbonilla.prioridades.di.domain.countries.countriesDomainModule
import com.cristhianbonilla.prioridades.di.domain.home.magazineListDomainModule
import com.cristhianbonilla.prioridades.di.domain.keywords.keywordsDomainModule
import com.cristhianbonilla.prioridades.di.domain.magazinepdf.magazinePdfDomainModule
import com.cristhianbonilla.prioridades.di.domain.payments.paymentsDomainModule
import com.cristhianbonilla.prioridades.di.domain.profile.profileDomainModule
import com.cristhianbonilla.prioridades.di.presentation.authentication.loginPresentationModule
import com.cristhianbonilla.prioridades.di.presentation.details.magazineDetailsModule
import com.cristhianbonilla.prioridades.di.presentation.home.homeModule
import com.cristhianbonilla.prioridades.di.presentation.profile.profilePresentationModule
import com.cristhianbonilla.prioridades.di.presentation.register.registerUserModule
import com.cristhianbonilla.prioridades.di.presentation.search.searchModule
import org.koin.core.module.Module

internal fun getModules() = mutableListOf<Module>().apply {
    addAll(getDomainModules())
    addAll(getDataModules())
    addAll(getPresentationModules())
    addAll(getCoreModule())
}

private fun getPresentationModules() =
    listOf(
        appModule,
        registerUserModule,
        homeModule,
        magazineDetailsModule,
        loginPresentationModule,
        profilePresentationModule
    )

private fun getDomainModules() =
    listOf(
        countriesDomainModule,
        keywordsDomainModule,
        magazineListDomainModule,
        magazinePdfDomainModule,
        profileDomainModule
    )

private fun getDataModules() =
    listOf(
        localDataModule,
        countryDataModule,
        keywordsDataModule,
        MagazineListDataModule,
        profileDataModule,
        paymentDataModule,
        paymentsDomainModule
    )

private fun getCoreModule()=
    listOf(
        networkModule,
        authenticationDataModule,
        securityModule,
        authenticationDomainModule,
        searchModule
    )