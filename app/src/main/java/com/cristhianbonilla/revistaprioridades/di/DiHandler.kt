package com.cristhianbonilla.revistaprioridades.di

import com.cristhianbonilla.revistaprioridades.di.data.authenticatin.authenticationDataModule
import com.cristhianbonilla.revistaprioridades.di.data.countries.countryDataModule
import com.cristhianbonilla.revistaprioridades.di.data.home.MagazineListDataModule
import com.cristhianbonilla.revistaprioridades.di.data.keywords.keywordsDataModule
import com.cristhianbonilla.revistaprioridades.di.data.local.localDataModule
import com.cristhianbonilla.revistaprioridades.di.data.networkModule
import com.cristhianbonilla.revistaprioridades.di.data.payments.paymentDataModule
import com.cristhianbonilla.revistaprioridades.di.data.profile.profileDataModule
import com.cristhianbonilla.revistaprioridades.di.data.securityModule
import com.cristhianbonilla.revistaprioridades.di.domain.authentication.authenticationDomainModule
import com.cristhianbonilla.revistaprioridades.di.domain.countries.countriesDomainModule
import com.cristhianbonilla.revistaprioridades.di.domain.home.magazineListDomainModule
import com.cristhianbonilla.revistaprioridades.di.domain.keywords.keywordsDomainModule
import com.cristhianbonilla.revistaprioridades.di.domain.magazinepdf.magazinePdfDomainModule
import com.cristhianbonilla.revistaprioridades.di.domain.payments.paymentsDomainModule
import com.cristhianbonilla.revistaprioridades.di.domain.profile.profileDomainModule
import com.cristhianbonilla.revistaprioridades.di.presentation.authentication.loginPresentationModule
import com.cristhianbonilla.revistaprioridades.di.presentation.details.magazineDetailsModule
import com.cristhianbonilla.revistaprioridades.di.presentation.home.homeModule
import com.cristhianbonilla.revistaprioridades.di.presentation.payment.paymentPresentationModule
import com.cristhianbonilla.revistaprioridades.di.presentation.profile.profilePresentationModule
import com.cristhianbonilla.revistaprioridades.di.presentation.register.registerUserModule
import com.cristhianbonilla.revistaprioridades.di.presentation.search.searchModule
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
        profilePresentationModule,
        paymentPresentationModule
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