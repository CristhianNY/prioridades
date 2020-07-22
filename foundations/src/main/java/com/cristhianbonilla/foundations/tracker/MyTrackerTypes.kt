package com.cristhianbonilla.foundations.tracker

interface MyTrackerTypes {
    fun type(): String
}

interface MyTrackerServiceType : MyTrackerTypes

interface MyTrackerScreenType : MyTrackerTypes

interface MyTrackerSectionFamily : MyTrackerTypes

interface MyTrackerSectionCategory : MyTrackerTypes

interface MyTrackerSectionName : MyTrackerTypes

interface MyTrackerInteractionType : MyTrackerTypes

interface MyTrackerEventNameType : MyTrackerTypes

interface MyTrackerUserStatus : MyTrackerTypes

interface MyTrackerUserType : MyTrackerTypes