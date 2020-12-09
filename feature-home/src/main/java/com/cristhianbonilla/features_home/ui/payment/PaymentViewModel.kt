package com.cristhianbonilla.features_home.ui.payment

import android.widget.Toast
import com.cristhianbonilla.custom_views.widget.creditCard.CreditCardData
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.model.countries.CountryItemModel
import com.cristhianbonilla.domain.model.countries.CountryModel
import com.cristhianbonilla.domain.model.payments.makePayment.response.MakePaymentResponseModel
import com.cristhianbonilla.domain.model.payments.response.CreditCardTokenModel
import com.cristhianbonilla.domain.model.profile.UserModel
import com.cristhianbonilla.domain.usecase.Scope
import com.cristhianbonilla.domain.usecase.UseCase
import com.cristhianbonilla.domain.usecase.contries.GetcountryUseCase
import com.cristhianbonilla.domain.usecase.paymets.GetCreditCardTokenizedUseCase
import com.cristhianbonilla.domain.usecase.paymets.MakePaymentTokenizedCardUseCase
import com.cristhianbonilla.domain.usecase.profile.ActivateUserUseCase
import com.cristhianbonilla.domain.usecase.profile.GetUserInformationUseCase
import com.cristhianbonilla.feature_home.R
import com.cristhianbonilla.features_home.ui.payment.mapper.MapperCreditCardRequest
import com.cristhianbonilla.foundations.base.BaseViewModel
import com.cristhianbonilla.foundations.extensions.context
import com.cristhianbonilla.foundations.extensions.execute

class PaymentViewModel(
    scope: Scope,
    data: PaymentData,
    tracker: PaymentTracker,
    private val getCountriesUseCase: GetcountryUseCase,
    private val getCreditCardTokenizedUseCase: GetCreditCardTokenizedUseCase,
    private val mapperCreditCardRequest: MapperCreditCardRequest,
    private val getUserInformationUseCase: GetUserInformationUseCase,
    private val makePaymentTokenizedCardUseCase: MakePaymentTokenizedCardUseCase,
    private val activateUserUseCase: ActivateUserUseCase
) : BaseViewModel<PaymentState, PaymentData, PaymentTracker>(scope, data, tracker) {

    private lateinit var creditCard: CreditCardData
    private lateinit var userModel: UserModel

    val paymentListener: (creditCardData: CreditCardData) -> Unit =
        { creditCardData: CreditCardData ->
            if (validateCreditCard(creditCardData)) {
                creditCard = creditCardData
                getUserInformation()

            } else {
                Toast.makeText(
                    context,
                    "Por favor ingresa todos los datos de la tarjeta",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    fun payWithPSE() {
       data.payWithPSE()
    }

    private fun validateCreditCard(creditCardData: CreditCardData): Boolean {
        if (creditCardData.creditCardNumber.isEmpty()
            || creditCardData.cardType.isEmpty() ||
            creditCardData.countryPayment.isEmpty() ||
            creditCardData.cvv.isEmpty() ||
            creditCardData.nameInCard.isEmpty() || creditCardData.countryPayment.isEmpty()
        ) {
            return false
        }
        return true
    }

    private fun tokenizedCreditCard(userModel: UserModel) {
        execute {
            getCreditCardTokenizedUseCase(
                GetCreditCardTokenizedUseCase.Params(
                    mapperCreditCardRequest.toTokenRequestModel(creditCard, userModel)
                )
            ).fold(
                {
                    data.updateErrorMessage(context.getString(R.string.login_error))
                    data.showEmptyState()
                }, ::handleTokenizedCard
            )
        }
    }

    private fun getUserInformation() {
        data.loading()
        execute {
            getUserInformationUseCase(UseCase.None).fold(
                ::handleUserInformationFail,
                ::handleUserInformationSucces
            )
        }
    }


    private fun handleTokenizedCard(creditCardTokenModel: CreditCardTokenModel) {
        if (creditCardTokenModel.code == ERROR_PAYU) {
            data.updateErrorMessage(context.getString(R.string.payment_error))
            data.showEmptyState()
        } else {
            makePayment(creditCardTokenModel)
        }

    }

    private fun makePayment(creditCardTokenModel: CreditCardTokenModel) {
        data.loading()
        execute {
            makePaymentTokenizedCardUseCase(
                MakePaymentTokenizedCardUseCase.Params(
                    mapperCreditCardRequest.toRequestPaymentModel(
                        creditCard,
                        userModel,
                        creditCardTokenModel.creditCardToken.creditCardTokenId.orEmpty()
                    )
                )
            ).fold(
                {
                    data.updateErrorMessage(context.getString(R.string.payment_error))
                    data.showEmptyState()
                }, ::handlePaymentCard
            )
        }
    }

    private fun handlePaymentCard(makePaymentResponseModel: MakePaymentResponseModel) {

        if (makePaymentResponseModel.code == ERROR_PAYU) {
            data.updateErrorMessage(makePaymentResponseModel.transactionResponseModel?.responseMessage)
        } else {

            if(makePaymentResponseModel.transactionResponseModel?.state == DECLINED){
                data.showEmptyState()
                data.updateErrorMessage(context.getString(R.string.payment_error))
            }else{
                activateSubscription()
            }

        }
    }

    private fun activateSubscription() {
        data.success()
        execute {
            activateUserUseCase(UseCase.None).fold(
                {
                    data.error()
                }, ::handleUserActiveSuccess
            )
        }
    }

    private fun handleUserActiveSuccess(userModel: UserModel) {
        data.updateStateToRenewSubscription()
    }

    fun getCountries() {
        execute {
            getCountriesUseCase(UseCase.None).fold(
                {
                    data.error()
                }, ::handleGetCountries
            )
        }
    }

    private fun handleGetCountries(countries: CountryModel) {
        data.hideEmptyState()
        val countriesString = ArrayList<CountryItemModel>()
        val countriesDialCode = ArrayList<String>()
        countries.countryList.forEach {
            countriesString.add(it)
            countriesDialCode.add(it.dialCode)
        }

        data.submitCountries(countriesString)
    }

    private fun handleUserInformationFail(failure: Failure) {

        data.error()
        when (failure) {
            is Failure.SessionExpired -> {
                data.updateErrorMessage(context.getString(R.string.login_error))
                data.showEmptyState()
            }

            is Failure.SubscriptionNotActivated -> {
                data.updateErrorMessage(context.getString(R.string.connection_error_text))
                data.showEmptyState()
            }
        }
    }

    private fun handleUserInformationSucces(userModel: UserModel) {
        this.userModel = userModel
        tokenizedCreditCard(userModel)
    }

    companion object {
        const val ERROR_PAYU = "ERROR"
        const val DECLINED = "DECLINED"
    }

}