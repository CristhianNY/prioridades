package com.cristhianbonilla.data.source.secure

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.usecase.UseCase.None
import com.cristhianbonilla.domain.functional.CustomResult
import kotlin.reflect.KClass

interface SecureLocalSource {

    fun <T> setValue(
        key: SecureKeyPreferences,
        value: T
    ): CustomResult<Failure, None>

    fun <T : Any> getValue(
        key: SecureKeyPreferences,
        klass: KClass<T>
    ): CustomResult<Failure, T>
}

enum class SecureKeyPreferences(val value: String) {
    USER_DOCUMENT_TYPE("userDocumentType"),
    USER_DOCUMENT("userDocument"),
    USER_REFRESH("userRefresh"),
    USER_APP("userApp"),
    LOGIN_NONCE("login_nonce")
}