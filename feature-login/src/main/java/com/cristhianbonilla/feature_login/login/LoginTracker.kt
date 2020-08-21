package com.cristhianbonilla.feature_login.login

import com.cristhianbonilla.foundations.base.BaseTracker
import com.cristhianbonilla.foundations.tracker.MyTrackerView
import com.cristhianbonilla.foundations.tracker.TrackerSectionCategory
import com.cristhianbonilla.foundations.tracker.TrackerSectionFamily
import com.cristhianbonilla.foundations.tracker.TrackerSectionName

private const val SCREEN_NAME = "landing_password"
private const val EVENT_LOGIN = "login"
private const val EVENT_LOGIN_SUCCESS = "login_success"
private const val EVENT_LOGIN_ERROR = "login_error"

class LoginTracker : BaseTracker() {

    override fun viewDisplayed() {
        view(
            MyTrackerView(
                SCREEN_NAME,
                TrackerSectionFamily.MAGAZINE,
                TrackerSectionCategory.AUTHENTICATION,
                TrackerSectionName.REGISTER
            )
        )
    }
}