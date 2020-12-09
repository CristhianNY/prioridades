package com.cristhianbonilla.data.source.remote.payment.mapper

import com.cristhianbonilla.data.entity.payments.makepayment.request.*
import com.cristhianbonilla.data.entity.payments.makepayment.response.MakePaymentResponseEntity
import com.cristhianbonilla.data.entity.payments.makepayment.response.TransactionResponse
import com.cristhianbonilla.data.entity.payments.request.CreditCardToken
import com.cristhianbonilla.data.entity.payments.request.CreditCardTokenRequest
import com.cristhianbonilla.data.entity.payments.request.Merchant
import com.cristhianbonilla.data.entity.payments.response.CreditCardTokenEntity
import com.cristhianbonilla.data.entity.payments.response.CreditCardTokenizedEntity
import com.cristhianbonilla.domain.model.payments.makePayment.request.*
import com.cristhianbonilla.domain.model.payments.makePayment.response.MakePaymentResponseModel
import com.cristhianbonilla.domain.model.payments.makePayment.response.TransactionResponseModel
import com.cristhianbonilla.domain.model.payments.request.CreditCardTokenRequestModel
import com.cristhianbonilla.domain.model.payments.response.CreditCardTokenModel
import com.cristhianbonilla.domain.model.payments.response.CreditCardTokenizedModel

class PaymentRemoteMapperImpl : PaymentRemoteMapper {
    override fun toRequest(creditCardTokenRequestModel: CreditCardTokenRequestModel): CreditCardTokenRequest {
        return CreditCardTokenRequest(
            creditCardTokenRequestModel.command,
            toCreditCardToken(
                creditCardTokenRequestModel.expirationDate,
                creditCardTokenRequestModel.name, creditCardTokenRequestModel.number,
                creditCardTokenRequestModel.payerId, creditCardTokenRequestModel.paymentMethod
            ),
            creditCardTokenRequestModel.language,
            toMerchant(creditCardTokenRequestModel.apiKey, creditCardTokenRequestModel.apiLogin)
        )
    }

    override fun toRequest(makePaymentRequestModel: MakePaymentRequestModel): MakePaymentRequestEntity {

        return MakePaymentRequestEntity(
            makePaymentRequestModel.command,
            makePaymentRequestModel.language,
            toEntity(makePaymentRequestModel.merchant),
            makePaymentRequestModel.test,
            toEntity(makePaymentRequestModel.transaction)

        )
    }


    private fun toCreditCardToken(
        expirationDate: String?,
        name: String?,
        number: String?,
        payerId: String?,
        paymentMethod: String?
    ): CreditCardToken {
        return CreditCardToken(
            expirationDate,
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

    override fun toModel(makePaymentResponseEntity: MakePaymentResponseEntity?): MakePaymentResponseModel {
        return MakePaymentResponseModel(
            makePaymentResponseEntity?.code.orEmpty(),
            makePaymentResponseEntity?.error.orEmpty(),
            toModel(makePaymentResponseEntity?.transactionResponse)
        )
    }

    override fun toModel(entity: TransactionResponse?): TransactionResponseModel {
        return TransactionResponseModel(
            entity?.authorizationCode.orEmpty(),
            entity?.errorCode.orEmpty(),
            entity?.extraParameters.orEmpty(),
            entity?.operationDate.orEmpty(),
            entity?.paymentNetworkResponseCode,
            entity?.paymentNetworkResponseErrorMessage.orEmpty(),
            entity?.pendingReason.orEmpty(),
            entity?.responseCode.orEmpty(),
            entity?.responseMessage.orEmpty(),
            entity?.state.orEmpty(),
            entity?.transactionDate.orEmpty(),
            entity?.transactionId.orEmpty(),
            entity?.transactionTime.orEmpty(),
            entity?.trazabilityCode.orEmpty()
        )
    }

    override fun toEntity(merchantModel: MerchantModel): MerchantEntity {
        return MerchantEntity(merchantModel.apiKey, merchantModel.apiLogin)
    }

    override fun toEntity(transactionModel: TransactionModel): TransactionEntity {
        return TransactionEntity(
            toEntity(transactionModel.creditCardModel),
            transactionModel.creditCardTokenId,
            transactionModel.ipAddress,
            toEntity(transactionModel.order),
            toEntity(transactionModel.payer),
            transactionModel.paymentCountry,
            transactionModel.paymentMethod,
            transactionModel.type,
            transactionModel.userAgent
        )
    }

    override fun toEntity(orderModel: OrderModel): Order {
        return Order(
            orderModel.accountId,
            toEntity(orderModel.additionalValues),
            toEntity(orderModel.buyer),
            orderModel.description,
            orderModel.language,
            orderModel.referenceCode,
            orderModel.signature
        )
    }

    override fun toEntity(payer: PayerModel): Payer {
        return Payer(
            payer.contactPhone,
            payer.dniNumber,
            payer.emailAddress,
            payer.fullName,
            payer.merchantPayerId
        )
    }

    override fun toEntity(additionalValuesModel: AdditionalValuesModel): AdditionalValuesEntity {
        return AdditionalValuesEntity(toEntity(additionalValuesModel.txValue))
    }

    override fun toEntity(buyerModel: BuyerModel): Buyer {
        return Buyer(
            buyerModel.contactPhone.orEmpty(),
            buyerModel.dniNumber.orEmpty(),
            buyerModel.emailAddress.orEmpty(),
            buyerModel.fullName.orEmpty(),
            buyerModel.merchantBuyerId.orEmpty()
        )
    }

    override fun toEntity(billingAddress: BillingAddressModel): BillingAddress {
        return BillingAddress(
            billingAddress.city,
            billingAddress.country,
            billingAddress.phone,
            billingAddress.postalCode,
            billingAddress.state,
            billingAddress.street1,
            billingAddress.street2
        )
    }

    override fun toEntity(txValueModel: TxValueModel): TxValueEntity {
        return TxValueEntity(txValueModel.currency, txValueModel.value)
    }

    override fun toEntity(creditCardModel: CreditCardModel): CreditCardEntity {
        return CreditCardEntity(creditCardModel.processWithoutCvv2, creditCardModel.securityCode)
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
