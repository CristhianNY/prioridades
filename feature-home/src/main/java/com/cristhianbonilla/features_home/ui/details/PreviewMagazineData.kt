package com.cristhianbonilla.features_home.ui.details

import com.cristhianbonilla.domain.model.home.MagazineModelItem
import com.cristhianbonilla.foundations.base.BaseData
import com.cristhianbonilla.features_home.ui.details.PreviewMagazineState.Error
import com.cristhianbonilla.features_home.ui.details.PreviewMagazineState.Success
import com.cristhianbonilla.features_home.ui.home.HomeMagazineState
import com.cristhianbonilla.foundations.livedata.MyLiveData

data class PreviewMagazineData(
    val magazineDescription: MyLiveData<String> = MyLiveData(""),
    val magazineImage: MyLiveData<String> = MyLiveData(""),
    val magazineDate: MyLiveData<String> = MyLiveData(""),
    val magazinePdf: MyLiveData<String> = MyLiveData(""),
    val magazineId: MyLiveData<String> = MyLiveData(""),
    val toolbar_title: MyLiveData<String> = MyLiveData("Cristhian")
) : BaseData<PreviewMagazineState>() {

    override fun loading() {
        super.loading()
        this updateState PreviewMagazineState.Loading
    }

    override fun error() {
        super.error()
        this updateState Error
    }

    fun setFragmentContent(item: MagazineModelItem) {
        magazineDescription update item.description
        magazineImage update item.image
        magazineDate update item.month + " " + item.monthName
        magazineId update item.id
    }

    fun setMagazinePdf(magazinePdfUrl: String) {
        magazinePdf update magazinePdfUrl
    }

    fun success() {
        showLoading.update(false)
        showError.update(false)
        updateState(Success)
    }

    fun sessionExpiredState() {
        updateState(PreviewMagazineState.SessionExpired)
    }

    fun subscriptionNotActivated() {
        updateState(PreviewMagazineState.SubscriptionNotActivated)
    }

    fun onReadMagazinePdf(magazine: String) {
        updateState(PreviewMagazineState.NavigateToMagazineReader(magazine))
    }

    fun onGoBack(){
        updateState(PreviewMagazineState.GoBack)
    }

}