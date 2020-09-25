package com.cristhianbonilla.domain.security

import com.cristhianbonilla.domain.exception.Failure
import java.security.Signature
import com.cristhianbonilla.domain.functional.CustomResult

interface ProviderSecurity {

    fun deleteKeyPair(): Boolean

    fun getPublicKeyBase64(): String?

    fun getSignature(): CustomResult<Failure.SecurityError, Signature>

    fun signData(signature: Signature, data: String): String?

    fun verify(encryptData: String, data: String): Boolean

    fun generateSignedJWT(signature: Signature): String?

}