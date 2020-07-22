package com.cristhianbonilla.foundations.tracker

data class MyTrackerEvent(
    val name: String,
    val category: String,
    val action: String,
    val label: String,
    val startArea: String? = null,
    val startSection: String? = null,
    val serviceType: MyTrackerServiceType? = null,
    val error: MyTrackerError? = null
)