package com.cristhianbonilla.features_home.ui.payment.mapper

import android.content.Context
import com.cristhianbonilla.custom_views.widget.creditCard.CreditCardData
import com.cristhianbonilla.domain.model.payments.makePayment.request.*
import com.cristhianbonilla.domain.model.payments.request.CreditCardTokenRequestModel
import com.cristhianbonilla.domain.model.profile.UserModel
import com.cristhianbonilla.foundations.extensions.md5
import java.time.LocalDateTime

class MapperCreditCardRequest(private val context: Context) {
    fun toTokenRequestModel(
        creditCardData: CreditCardData,
        userModel: UserModel
    ): CreditCardTokenRequestModel {

        return CreditCardTokenRequestModel(
            expirationDate = creditCardData.expirationDate,
            name = creditCardData.nameInCard,
            number = creditCardData.creditCardNumber,
            payerId = userModel.user?.id,
            paymentMethod = creditCardData.cardType
        )
    }

    fun toRequestPaymentModel(
        creditCardData: CreditCardData,
        userModel: UserModel,
        creditCardToken: String
    ): MakePaymentRequestModel {
        return MakePaymentRequestModel(
            COMMAND, LANGUAGE, MerchantModel(API_KEY, API_lOGIN), IS_TEST,
            TransactionModel(
                creditCardModel = CreditCardModel("false", creditCardData.cvv),
                cookie = "",
                creditCardTokenId = creditCardToken,
                deviceSessionId = "",
                ipAddress = getIp(),
                order = OrderModel(
                    accountId = ACCOUNT_ID,
                    additionalValues = AdditionalValuesModel(TxValueModel(CURRENCY, VALUE)),
                    buyer = BuyerModel(
                        contactPhone = userModel.user?.telefono,
                        dniNumber = "",
                        emailAddress = "",
                        fullName = userModel.user?.nombres,
                        merchantBuyerId = ""
                    ),
                    description = DESCRIPTION,
                    language = LANGUAGE,
                    notifyUrl = "",
                    referenceCode = getReferenceCode(userModel),
                    signature = API_KEY.plus("~").plus(MERCHANT_ID).plus("~")
                        .plus(getReferenceCode(userModel))
                        .plus("~")
                        .plus(VALUE).plus("~").plus(CURRENCY).md5()
                ),
                payer = PayerModel(
                    userModel.user?.telefono.orEmpty(),
                    "",
                    "",
                    userModel.user?.nombres.orEmpty(),
                    creditCardData.cardType
                ),
                paymentCountry = creditCardData.countryPayment,
                paymentMethod = creditCardData.cardType,
                type = TYPE,
                userAgent = ""
            )
        )
    }

    private fun getIp(): String {

        return "127.0.0.0"
    }

    private fun getReferenceCode(userModel: UserModel): String {
        return userModel.user?.id.toString().plus(LocalDateTime.now())
    }

    companion object {
        const val COMMAND = "SUBMIT_TRANSACTION"
        const val LANGUAGE = "es"
        const val API_KEY = "F4vaKr28jSOCdC3Tx2IMCD8XW5"
        const val API_lOGIN = "AZX1vW8cw5Aq36B"
        const val DESCRIPTION = "Pago subscripción revista prioridades"
        const val TYPE = "AUTHORIZATION_AND_CAPTURE"
        const val MERCHANT_ID = "585281"
        const val VALUE = 18000
        const val CURRENCY = "COP"
        const val ACCOUNT_ID = "588264"
        const val IS_TEST = true
    }
}