package com.cristhianbonilla.features_home.ui.search

import com.cristhianbonilla.foundations.base.BaseTracker
import com.cristhianbonilla.foundations.tracker.MyTrackerView
import com.cristhianbonilla.foundations.tracker.TrackerSectionCategory
import com.cristhianbonilla.foundations.tracker.TrackerSectionFamily
import com.cristhianbonilla.foundations.tracker.TrackerSectionName

private const val SCREEN_NAME = "search_tracker"

class SearchTracker : BaseTracker() {
    override fun viewDisplayed() {
        view(
            MyTrackerView(
                SCREEN_NAME,
                TrackerSectionFamily.PROFILE,
                TrackerSectionCategory.AUTHENTICATION,
                TrackerSectionName.REGISTER
            )
        )
    }
}