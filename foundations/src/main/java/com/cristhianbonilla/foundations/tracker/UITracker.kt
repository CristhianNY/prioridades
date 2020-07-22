package com.cristhianbonilla.foundations.tracker

internal lateinit var uiTracker: UITracker

interface UITracker {

    fun initialize()

    fun setUserData(userDataModel: MyTrackerUserData)

    fun view(view: MyTrackerView)

    fun event(event: MyTrackerEvent)
}