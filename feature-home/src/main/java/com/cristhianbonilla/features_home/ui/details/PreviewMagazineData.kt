package com.cristhianbonilla.features_home.ui.details

import com.cristhianbonilla.domain.model.home.MagazineModelItem
import com.cristhianbonilla.foundations.base.BaseData
import com.cristhianbonilla.features_home.ui.details.PreviewMagazineState.Error
import com.cristhianbonilla.features_home.ui.details.PreviewMagazineState.Success
import com.cristhianbonilla.foundations.livedata.MyLiveData

data class PreviewMagazineData(
    val magazineDescription:MyLiveData<String> = MyLiveData(""),
    val magazineImage:MyLiveData<String> = MyLiveData(""),
    val magazineDate:MyLiveData<String> = MyLiveData("")
): BaseData<PreviewMagazineState>() {

    override fun loading() {
        super.loading()
        this updateState PreviewMagazineState.Loading
    }

    override fun error() {
        super.error()
        this updateState Error
    }

    fun setFragmentContent(item: MagazineModelItem){
        magazineDescription update item.description
        magazineImage update item.image
        magazineDate update item.month
    }

    fun success() {
        showLoading.update(false)
        showError.update(false)
        updateState(Success)
    }

}