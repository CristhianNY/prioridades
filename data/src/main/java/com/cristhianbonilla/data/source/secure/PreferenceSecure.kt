package com.cristhianbonilla.data.source.secure

import com.cristhianbonilla.data.main.preference.Preferences
import com.cristhianbonilla.data.main.security.Encryptar
import com.cristhianbonilla.data.main.security.HashSecurity
import com.cristhianbonilla.data.reporter.reporter
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.Result
import com.cristhianbonilla.domain.functional.Result.Error
import com.cristhianbonilla.domain.functional.Result.Success
import com.cristhianbonilla.domain.usecase.UseCase
import kotlin.reflect.KClass

class PreferenceSecure(
    private val prefs: Preferences,
    private val hash: HashSecurity,
    private val encryption: Encryptar) : SecureLocalSource{

    override fun <T> setValue(
        key: SecureKeyPreferences,
        value: T
    ): Result<Failure, UseCase.None> =
        try {
            val hastText = hash.getHash(key.value)
            val textEncrypt = when (value) {
                is Boolean,
                is Int,
                is Float,
                is String,
                is Long -> encryption.encrypt(value.toString())
                else -> throw Exception("Unhandled set type")
            }
            prefs.setValue(hastText, textEncrypt)
            Success(UseCase.None)
        } catch (e: Exception) {
            reporter.exception(e)
            Error(Failure.LocalError)
        }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> getValue(
        key: SecureKeyPreferences,
        klass: KClass<T>
    ): Result<Failure, T> =
        try {
            val hastText = hash.getHash(key.value)
            val textDecrypt = encryption.decrypt(prefs.getValue(hastText, String::class))
            val result: T = when (klass) {
                Boolean::class -> textDecrypt.toBoolean() as T
                Int::class -> textDecrypt.toInt() as T
                Float::class -> textDecrypt.toFloat() as T
                String::class -> textDecrypt as T
                Long::class -> textDecrypt.toLong() as T
                else -> throw Exception("Unhandled return type")
            }
            Success(result)
        } catch (e: Exception) {
            reporter.exception(e)
            Error(Failure.LocalError)
        }
}