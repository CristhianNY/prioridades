package com.cristhianbonilla.features_home.ui.payment

import com.cristhianbonilla.foundations.base.BaseTracker
import com.cristhianbonilla.foundations.tracker.MyTrackerView
import com.cristhianbonilla.foundations.tracker.TrackerSectionCategory
import com.cristhianbonilla.foundations.tracker.TrackerSectionFamily
import com.cristhianbonilla.foundations.tracker.TrackerSectionName

private const val SCREEN_NAME = "front_credit_card_payment"

class PaymentTracker() : BaseTracker() {

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