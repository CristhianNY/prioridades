package com.cristhianbonilla.features_home.ui.perfil

import com.cristhianbonilla.domain.model.home.MagazineModelItem
import com.cristhianbonilla.domain.model.profile.UserModel
import com.cristhianbonilla.features_home.ui.details.PreviewMagazineState
import com.cristhianbonilla.features_home.ui.home.HomeMagazineState
import com.cristhianbonilla.foundations.base.BaseData
import com.cristhianbonilla.foundations.livedata.MyLiveData

class ProfileData(
    val fullName: MyLiveData<String> = MyLiveData(""),
    val email: MyLiveData<String> = MyLiveData(""),
    val phone: MyLiveData<String> = MyLiveData(""),
    val country: MyLiveData<String> = MyLiveData(""),
    val city: MyLiveData<String> = MyLiveData(""),
    val subscription: MyLiveData<String> = MyLiveData("")
) : BaseData<ProfileState>() {

    override fun loading() {
        super.loading()
        this updateState ProfileState.Loading
    }

    fun success() {
        showLoading.update(false)
        showError.update(false)
        updateState(ProfileState.Success)
    }

    override fun error() {
        super.error()
        this updateState ProfileState.Error
    }

    fun setFragmentContent(user: UserModel) {
        fullName update user.user?.nombres.orEmpty()
        email update user.user?.email.orEmpty()
        phone update user.user?.telefono.orEmpty()
        country update user.user?.pais.orEmpty()
        city update user.user?.ciudad.orEmpty()
        subscription update user.user?.fechaFin.orEmpty()
    }
}