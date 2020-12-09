package com.cristhianbonilla.data.source.remote.payment.api

import com.cristhianbonilla.data.entity.payments.makepayment.request.MakePaymentRequestEntity
import com.cristhianbonilla.data.entity.payments.makepayment.response.MakePaymentResponseEntity
import com.cristhianbonilla.data.entity.payments.request.CreditCardTokenRequest
import com.cristhianbonilla.data.entity.payments.response.CreditCardTokenEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PayULatamApi {

    @POST("4.0/service.cgi")
    suspend fun getCreditCardToken(
        @Body creditCardTokenRequest: CreditCardTokenRequest
    ): Response<CreditCardTokenEntity>

    @POST("4.0/service.cgi")
    suspend fun makePayment(
        @Body makePaymentRequestEntity: MakePaymentRequestEntity
    ): Response<MakePaymentResponseEntity>

}