package com.cristhianbonilla.data.session

import android.util.Log
import com.cristhianbonilla.data.source.remote.authenication.AuthRemoteSource
import com.cristhianbonilla.data.source.secure.SecureKeyPreferences.USER_REFRESH
import com.cristhianbonilla.data.source.secure.SecureLocalSource
import com.cristhianbonilla.domain.functional.getOrElse
import com.cristhianbonilla.domain.session.Session

class SessionHandler(
    private val authRemoteSource: AuthRemoteSource,
    private val secureLocalSource: SecureLocalSource
) : Session {

    companion object {
        private const val DEFAULT_VALUE = ""
    }

    private var accessToken: String = ""


    override suspend fun login(accessToken: String) {
        storeData(accessToken,"")
    }

    override suspend fun logout(): Boolean {
        return deleteData()
    }

    override suspend fun refresh(): Boolean {
        return false
    }

     override fun isTokenSaved():Boolean{
        return secureLocalSource.getValue(USER_REFRESH, String::class).isSuccess
    }

    override fun isLogged():Boolean {
        return accessToken.isNotEmpty()
    }

    override fun getAccessToken(): String = accessToken

    override fun getRefreshToken(): String =
        secureLocalSource.getValue(USER_REFRESH, String::class).getOrElse("")

    private fun storeData(access: String, refresh: String): Boolean {
        secureLocalSource.setValue(USER_REFRESH, access)
        this.accessToken = access
        return true
    }

    private fun deleteData(): Boolean {
        Log.d("Cristhian","Se llamo eliminar")
        return storeData(DEFAULT_VALUE, DEFAULT_VALUE)
    }

}