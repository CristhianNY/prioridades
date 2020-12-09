package com.cristhianbonilla.data.source.remote.payment.mapper

import com.cristhianbonilla.data.entity.payments.makepayment.request.*
import com.cristhianbonilla.data.entity.payments.makepayment.response.MakePaymentResponseEntity
import com.cristhianbonilla.data.entity.payments.makepayment.response.TransactionResponse
import com.cristhianbonilla.data.entity.payments.request.CreditCardTokenRequest
import com.cristhianbonilla.data.entity.payments.request.Merchant
import com.cristhianbonilla.data.entity.payments.response.CreditCardTokenEntity
import com.cristhianbonilla.domain.model.payments.makePayment.request.*
import com.cristhianbonilla.domain.model.payments.makePayment.response.MakePaymentResponseModel
import com.cristhianbonilla.domain.model.payments.makePayment.response.TransactionResponseModel
import com.cristhianbonilla.domain.model.payments.request.CreditCardTokenRequestModel
import com.cristhianbonilla.domain.model.payments.response.CreditCardTokenModel
import com.cristhianbonilla.domain.model.payments.response.CreditCardTokenizedModel

interface PaymentRemoteMapper {
    fun toRequest(creditCardTokenRequestModel: CreditCardTokenRequestModel): CreditCardTokenRequest
    fun toModel(creditCardTokenEntity: CreditCardTokenEntity?): CreditCardTokenModel
    fun toRequest(makePaymentRequestModel: MakePaymentRequestModel): MakePaymentRequestEntity
    fun toEntity(merchantModel: MerchantModel): MerchantEntity
    fun toEntity(transactionModel: TransactionModel): TransactionEntity
    fun toEntity(orderModel: OrderModel): Order
    fun toEntity(payer: PayerModel): Payer
    fun toEntity(additionalValuesModel: AdditionalValuesModel): AdditionalValuesEntity
    fun toEntity(buyerModel: BuyerModel): Buyer
    fun toEntity(billingAddress: BillingAddressModel): BillingAddress
    fun toEntity(txValueModel: TxValueModel): TxValueEntity
    fun toEntity(creditCardModel: CreditCardModel): CreditCardEntity
    fun toModel(makePaymentResponseEntity: MakePaymentResponseEntity?): MakePaymentResponseModel
    fun toModel(transactionResponse: TransactionResponse?): TransactionResponseModel
}