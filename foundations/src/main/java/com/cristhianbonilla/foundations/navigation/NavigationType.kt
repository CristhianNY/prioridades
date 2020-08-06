package com.cristhianbonilla.foundations.navigation

sealed class NavigationType(type: ModelNavType, data: NavData? = null) : NavParams(type, data) {

    class ViewMagazineDetailsNavParams : NavigationType(ModelNavType.ViewMagazineDetailsType)
}


sealed class ModelNavType : NavType {
    object LandingNavType : ModelNavType()
    object OnboardingType : ModelNavType()
    object HomeNavType : ModelNavType()
    object HybridType : ModelNavType()
    object WebViewTestType : ModelNavType()
    object AccountMoreOptionType : ModelNavType()
    object AccountType : ModelNavType()
    object ProfileType : ModelNavType()
    object DepositsType : ModelNavType()
    object ContactsType : ModelNavType()
    object SecurityType : ModelNavType()
    object SelectFromDefaultAvatarsType : ModelNavType()
    object GlobalPositionPreferencesNavType : ModelNavType()
    object ViewMagazineDetailsType : ModelNavType()
    object SuccessScreenType : ModelNavType()
    object ErrorScreenType : ModelNavType()
}