package com.cristhianbonilla.features_home.ui.payment

import com.cristhianbonilla.custom_views.widget.creditCard.CreditCardData
import com.cristhianbonilla.domain.model.countries.CountryItemModel
import com.cristhianbonilla.foundations.base.BaseData
import com.cristhianbonilla.foundations.livedata.MyLiveData

class PaymentData(
    var countryList: MyLiveData<List<CountryItemModel>> = MyLiveData(mutableListOf()),
    var paymentUrl: MyLiveData<String> = MyLiveData("https://biz.payulatam.com/L08ee41AFCA960A"),
    var isEmpty:MyLiveData<Boolean> = MyLiveData(false),
    var showContent:MyLiveData<Boolean> = MyLiveData(true),
    var errorMessage:MyLiveData<String> = MyLiveData("")
) : BaseData<PaymentState>() {


    internal fun makePayment(creditCardData: CreditCardData){


    }

    fun submitCountries(countries: List<CountryItemModel>) {
        countryList update countries
    }

    fun updateStateToLoginRequired() {

    }

    fun showEmptyState(){
        isEmpty update true
        showContent update false
        showLoading update false
    }

    fun success() {
        showLoading.update(false)
        showError.update(false)
    }

    fun hideEmptyState(){
        isEmpty update false
        showContent update true
    }

    fun payWithPSE(){
        updateState(PaymentState.PayWIthPSE)
    }

    fun updateStateToRenewSubscription() {
        updateState(PaymentState.UserActivated)
    }

    fun updateErrorMessage(errorMessage: String?) {
        if (errorMessage != null) {
            this.errorMessage update errorMessage
        }
    }

    companion object {
    }

}