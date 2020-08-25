package com.cristhianbonilla.features_home.ui.perfil

import com.cristhianbonilla.foundations.base.BaseTracker
import com.cristhianbonilla.foundations.tracker.MyTrackerView
import com.cristhianbonilla.foundations.tracker.TrackerSectionCategory
import com.cristhianbonilla.foundations.tracker.TrackerSectionFamily
import com.cristhianbonilla.foundations.tracker.TrackerSectionName

private const val SCREEN_NAME = "user_profile"

class ProfileTracker : BaseTracker() {
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