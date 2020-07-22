package com.cristhianbonilla.prioridades.di

import com.cristhianbonilla.prioridades.di.data.countries.countryDataModule
import com.cristhianbonilla.prioridades.di.domain.countries.countriesDomainModule
import org.koin.core.module.Module

internal fun getModules() = mutableListOf<Module>().apply {
    addAll(getDomainModules())
    addAll(getDataModules())
}

private fun getPresentationModules() =
    listOf(
        appModule
    )

private fun getDomainModules() =
    listOf(
        countriesDomainModule
    )

private fun getDataModules() =
    listOf(
        countryDataModule
    )