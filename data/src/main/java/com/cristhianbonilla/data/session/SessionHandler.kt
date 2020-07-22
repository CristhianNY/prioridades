package com.cristhianbonilla.data.session

import com.cristhianbonilla.data.source.remote.authenication.AuthRemoteSource
import com.cristhianbonilla.data.source.secure.SecureLocalSource
import com.cristhianbonilla.data.source.secure.SecureKeyPreferences.USER_DOCUMENT
import com.cristhianbonilla.data.source.secure.SecureKeyPreferences.USER_REFRESH
import com.cristhianbonilla.domain.functional.getOrElse
import com.cristhianbonilla.domain.functional.getOrNull
import com.cristhianbonilla.domain.session.Session

class SessionHandler(
    private val authRemoteSource: AuthRemoteSource,
    private val secureLocalSource: SecureLocalSource
) : Session {

    companion object {
        private const val DEFAULT_VALUE = ""
    }

    private var accessToken: String = ""

    override suspend fun login(accessToken: String, refreshToken: String) {
        storeData(accessToken, refreshToken)
    }

    override suspend fun logout(): Boolean {
        authRemoteSource.postLogout(accessToken)
        return deleteData()
    }

    override suspend fun refresh(): Boolean {
        var isRefreshed = false
        val document = secureLocalSource.getValue(USER_DOCUMENT, String::class).getOrNull()
        val refreshToken = secureLocalSource.getValue(USER_REFRESH, String::class).getOrNull()
        if (document != null && refreshToken != null) {
            authRemoteSource.postRefresh(document, accessToken, refreshToken)
                .getOrNull()
                ?.let { userAuthModel ->
                    storeData(userAuthModel.accessToken, userAuthModel.refreshToken)
                    isRefreshed = true
                }
        }
        return isRefreshed
    }

    override fun isLogged() = accessToken.isNotEmpty()

    override fun getAccessToken(): String = accessToken

    override fun getRefreshToken(): String =
        secureLocalSource.getValue(USER_REFRESH, String::class).getOrElse("")

    private fun storeData(access: String, refresh: String): Boolean {
        secureLocalSource.setValue(USER_REFRESH, refresh)
        this.accessToken = access
        return true
    }

    private fun deleteData(): Boolean =
        storeData(DEFAULT_VALUE, DEFAULT_VALUE)
}