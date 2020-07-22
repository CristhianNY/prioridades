package com.cristhianbonilla.foundations.tracker

data class MyTrackerUserData(
    val appLang: String? = null,
    val userModelType: MyTrackerUserType? = null,
    val userModelStatus: MyTrackerUserStatus? = null,
    val userMarket: String? = null,
    val userId: String? = null,
    val userIp: String? = null
)