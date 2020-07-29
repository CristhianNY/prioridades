package com.cristhianbonilla.prioridades.di

import com.cristhianbonilla.prioridades.di.data.countries.countryDataModule
import com.cristhianbonilla.prioridades.di.data.keywords.keywordsDataModule
import com.cristhianbonilla.prioridades.di.data.local.localDataModule
import com.cristhianbonilla.prioridades.di.domain.countries.countriesDomainModule
import com.cristhianbonilla.prioridades.di.domain.keywords.keywordsDomainModule
import com.cristhianbonilla.prioridades.di.presentation.home.homeModule
import com.cristhianbonilla.prioridades.di.presentation.register.registerUserModule
import org.koin.core.module.Module

internal fun getModules() = mutableListOf<Module>().apply {
    addAll(getDomainModules())
    addAll(getDataModules())
    addAll(getPresentationModules())
}

private fun getPresentationModules() =
    listOf(
        appModule,
        registerUserModule,
        homeModule
    )

private fun getDomainModules() =
    listOf(
        countriesDomainModule,
        keywordsDomainModule
    )

private fun getDataModules() =
    listOf(
        localDataModule,
        countryDataModule,
        keywordsDataModule
    )