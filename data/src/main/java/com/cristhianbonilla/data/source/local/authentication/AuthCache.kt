package com.cristhianbonilla.data.source.local.authentication

import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.functional.CustomResult

class AuthCache : AuthLocalSource {


    private var mDocumentType: String? = null
    private var mUsername: String? = null

    override suspend fun setUser(
        documentType: String,
        username: String
    ) {
        this.mDocumentType = documentType
        this.mUsername = username
    }

    override suspend fun getUser(): CustomResult<Failure, Pair<String, String>> =
        doAction {
            if (mDocumentType != null && mUsername != null) {
                Pair(mDocumentType!!, mUsername!!)
            } else {
                throw Exception()
            }
        }

    override fun invalidate() {
        mDocumentType = null
        mUsername = null
    }
}