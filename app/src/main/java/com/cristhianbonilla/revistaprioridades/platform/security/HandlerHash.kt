package com.cristhianbonilla.revistaprioridades.platform.security

import com.cristhianbonilla.data.main.security.AlgorithmHash
import com.cristhianbonilla.data.main.security.HashSecurity
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and

class HandlerHash : HashSecurity {

    override fun getHash(
        textToHash: String,
        algorithm: AlgorithmHash
    ): String =
        try {
            val md = MessageDigest.getInstance(algorithm.value)
            val digest = md.digest(textToHash.toByteArray())
            val sb = java.lang.StringBuilder()
            for (i in digest.indices) {
                sb.append(((digest[i] and 0xff.toByte()) + 0x100).toString(16).substring(1))
            }
            sb.toString()
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("Failed to get a hash of text", e)
        }
}