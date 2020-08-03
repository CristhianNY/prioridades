package com.cristhianbonilla.data.main.security

interface Encryptar {
    fun encrypt(toEncrypt: String): String

    fun decrypt(toDecrypt: String): String
}