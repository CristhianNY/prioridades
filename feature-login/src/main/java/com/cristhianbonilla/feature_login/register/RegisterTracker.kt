package com.cristhianbonilla.feature_login.register

import com.cristhianbonilla.foundations.base.BaseTracker
import com.cristhianbonilla.foundations.tracker.MyTrackerView
import com.cristhianbonilla.foundations.tracker.TrackerSectionFamily.END_REGISTER_USER
import com.cristhianbonilla.foundations.tracker.TrackerSectionCategory.AUTHENTICATION
import com.cristhianbonilla.foundations.tracker.TrackerSectionName.REGISTER

private const val SCREEN_NAME = "end_registration"

class RegisterTracker : BaseTracker() {

    override fun viewDisplayed() {
        view(MyTrackerView(SCREEN_NAME, END_REGISTER_USER, AUTHENTICATION, REGISTER))
    }
}