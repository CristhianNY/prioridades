package com.cristhianbonilla.foundations.base

import com.cristhianbonilla.foundations.tracker.MyTrackerEvent
import com.cristhianbonilla.foundations.tracker.MyTrackerView
import com.cristhianbonilla.foundations.tracker.uiTracker

abstract class BaseTracker {

    abstract fun viewDisplayed()

    fun view(view: MyTrackerView) {
        uiTracker.view(view)
    }

    fun event(event: MyTrackerEvent) {
        uiTracker.event(event)
    }
}