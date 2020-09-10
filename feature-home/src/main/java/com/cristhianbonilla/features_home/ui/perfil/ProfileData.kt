package com.cristhianbonilla.features_home.ui.perfil

import androidx.annotation.StringRes
import com.cristhianbonilla.domain.model.home.MagazineModelItem
import com.cristhianbonilla.domain.model.profile.UserModel
import com.cristhianbonilla.feature_home.R
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
    val subscription: MyLiveData<String> = MyLiveData(""),
    var errorMessage: MyLiveData<Int> = MyLiveData(R.string.connection_error_text),
    var showContent: MyLiveData<Boolean> = MyLiveData(false)
) : BaseData<ProfileState>() {

    override fun loading() {
        super.loading()
        this updateState ProfileState.Loading
    }

    fun success() {
        showLoading.update(false)
        showError.update(false)
        showContent update (true)
        updateState(ProfileState.Success)
    }

    override fun error() {
        super.error()
        showContent update (false)
        this updateState ProfileState.Error
    }

    fun setFragmentContent(user: UserModel) {
        fullName update user.user?.nombres.orEmpty() + user.user?.apellidos.orEmpty()
        email update user.user?.email.orEmpty()
        phone update user.user?.telefono.orEmpty()
        country update user.user?.pais.orEmpty()
        city update user.user?.ciudad.orEmpty()
        subscription update user.user?.fechaFin.orEmpty()
    }

    fun updateStateToLoginRequired() {
        this updateState ProfileState.LoginRequired
    }

    fun updateStateToRenewSubscription() {
        this updateState ProfileState.RenewSubscription
    }

    fun updateErrorMessage(@StringRes errorMessage: Int) {
        this.errorMessage update errorMessage
    }
}