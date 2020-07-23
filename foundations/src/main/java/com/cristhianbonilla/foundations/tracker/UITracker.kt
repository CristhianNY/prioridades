package com.cristhianbonilla.foundations.tracker


interface UITracker {

    fun initialize()

    fun setUserData(userDataModel: MyTrackerUserData)

    fun view(view: MyTrackerView)

    fun event(event: MyTrackerEvent)
}