package com.cristhianbonilla.domain.model.countries

data class CountryItemModel(
    val code: String,
    val dialCode: String,
    val name: String
) {
    override fun toString() = name
}