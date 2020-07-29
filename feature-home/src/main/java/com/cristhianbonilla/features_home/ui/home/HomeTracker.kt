package com.cristhianbonilla.features_home.ui.home

import com.cristhianbonilla.foundations.base.BaseTracker
import com.cristhianbonilla.foundations.tracker.MyTrackerView
import com.cristhianbonilla.foundations.tracker.TrackerSectionFamily.MAGAZINE
import com.cristhianbonilla.foundations.tracker.TrackerSectionCategory.AUTHENTICATION
import com.cristhianbonilla.foundations.tracker.TrackerSectionName.REGISTER

private const val SCREEN_NAME = "home_screen"

class HomeTracker() : BaseTracker() {

    override fun viewDisplayed() {
        view(
            MyTrackerView(
                SCREEN_NAME,
                MAGAZINE,
                AUTHENTICATION,
                REGISTER
            )
        )
    }
}