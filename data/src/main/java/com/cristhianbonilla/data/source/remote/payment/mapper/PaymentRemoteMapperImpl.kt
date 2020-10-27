package com.cristhianbonilla.data.source.remote.payment.mapper

import com.cristhianbonilla.data.entity.payments.request.CreditCardToken
import com.cristhianbonilla.data.entity.payments.request.CreditCardTokenRequest
import com.cristhianbonilla.data.entity.payments.request.Merchant
import com.cristhianbonilla.data.entity.payments.response.CreditCardTokenEntity
import com.cristhianbonilla.data.entity.payments.response.CreditCardTokenizedEntity
import com.cristhianbonilla.domain.model.payments.request.CreditCardTokenRequestModel
import com.cristhianbonilla.domain.model.payments.response.CreditCardTokenModel
import com.cristhianbonilla.domain.model.payments.response.CreditCardTokenizedModel

class PaymentRemoteMapperImpl : PaymentRemoteMapper {


    override fun toRequest(creditCardTokenRequestModel: CreditCardTokenRequestModel): CreditCardTokenRequest {
        return CreditCardTokenRequest(
            creditCardTokenRequestModel.command,
            toCreditCardToken(
                creditCardTokenRequestModel.expirationDate,
                creditCardTokenRequestModel.identificationNumber,
                creditCardTokenRequestModel.name, creditCardTokenRequestModel.number,
                creditCardTokenRequestModel.payerId, creditCardTokenRequestModel.paymentMethod
            ),
            creditCardTokenRequestModel.language,
            toMerchant(creditCardTokenRequestModel.apiKey, creditCardTokenRequestModel.apiLogin)
        )
    }


    private fun toCreditCardToken(
        expirationDate: String?,
        identificationNumber: String?,
        name: String?,
        number: String?,
        payerId: String?,
        paymentMethod: String?
    ): CreditCardToken {
        return CreditCardToken(
            expirationDate,
            identificationNumber,
            name,
            number,
            payerId,
            paymentMethod
        )
    }

    private fun toMerchant(apiKey: String?, apiLogin: String?): Merchant {
        return Merchant(apiKey, apiLogin)
    }

    override fun toModel(creditCardTokenEntity: CreditCardTokenEntity?): CreditCardTokenModel {
        return CreditCardTokenModel(
            creditCardTokenEntity?.code.orEmpty(),
            toCreditCardTokenizedModel(creditCardTokenEntity?.creditCardTokenizedEntity)
        )
    }


    private fun toCreditCardTokenizedModel(creditCardToken: CreditCardTokenizedEntity?): CreditCardTokenizedModel {

        return CreditCardTokenizedModel(
            creditCardToken?.creationDate.orEmpty(),
            creditCardToken?.creditCardTokenId.orEmpty(),
            creditCardToken?.errorDescription.orEmpty(),
            creditCardToken?.expirationDate.orEmpty(),
            creditCardToken?.identificationNumber?.orEmpty(),
            creditCardToken?.maskedNumber.orEmpty(),
            creditCardToken?.name.orEmpty(),
            creditCardToken?.number.orEmpty(),
            creditCardToken?.payerId.orEmpty(),
            creditCardToken?.paymentMethod.orEmpty()
        )

    }


}
