package com.cristhianbonilla.data.main.security

interface HashSecurity {
    fun getHash(
        textToHash: String,
        algorithm: AlgorithmHash = AlgorithmHash.SHA_512
    ): String
}