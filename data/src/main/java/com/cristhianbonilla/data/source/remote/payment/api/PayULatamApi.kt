package com.cristhianbonilla.data.source.remote.payment.api

import com.cristhianbonilla.data.entity.payments.request.CreditCardTokenRequest
import com.cristhianbonilla.data.entity.payments.response.CreditCardTokenEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface PayULatamApi {
    @GET("/service.cgi")
    suspend fun getCreditCardToken(
        @Body creditCardTokenRequest: CreditCardTokenRequest
    ): Response<CreditCardTokenEntity>
}