package com.cristhianbonilla.revistaprioridades.platform.security

import android.annotation.TargetApi
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties.*
import android.util.Base64
import com.cristhianbonilla.data.main.security.Encryptar
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.security.*
import java.security.cert.CertificateException
import javax.crypto.*
import javax.crypto.spec.IvParameterSpec


@TargetApi(Build.VERSION_CODES.M)
class AESEncryption : Encryptar {

    companion object {
        private const val KEY_STORE = "AndroidKeyStore"
        private const val KEY_ALIAS = "aliasOK"
        private const val TRANSFORMATION =
            "$KEY_ALGORITHM_AES/$BLOCK_MODE_CBC/$ENCRYPTION_PADDING_PKCS7"
        private const val SEPARATOR = "]"
    }

    private val keyStore: KeyStore by lazy { setupKeyStore() }
    private val keyGenerator: KeyGenerator by lazy { setupKeyGenerator() }

    override fun encrypt(toEncrypt: String): String =
        try {
            val cipher = Cipher.getInstance(TRANSFORMATION)
                .apply {
                    init(Cipher.ENCRYPT_MODE, getSecretKey())
                }
            val iv = Base64.encodeToString(cipher.iv, Base64.DEFAULT)
            val encrypted = Base64.encodeToString(
                cipher.doFinal(toEncrypt.toByteArray(StandardCharsets.UTF_8)),
                Base64.DEFAULT
            )
            encrypted + SEPARATOR + iv
        } catch (e: Exception) {
            when (e) {
                is NoSuchAlgorithmException,
                is NoSuchPaddingException -> throw RuntimeException("Failed to get an instance of Cipher", e)
                is BadPaddingException,
                is IllegalBlockSizeException ->
                    throw RuntimeException("Failed to encrypt the data with the generated key. Retry the purchase")
                else -> throw e
            }
        }

    override fun decrypt(toDecrypt: String): String =
        try {
            val parts: List<String> = toDecrypt.split(SEPARATOR)
            if (parts.size != 2) throw AssertionError("")
            val encrypted = Base64.decode(parts[0], Base64.DEFAULT)
            val iv = Base64.decode(parts[1], Base64.DEFAULT)
            val cipher = Cipher.getInstance(TRANSFORMATION)
                .apply {
                    init(Cipher.DECRYPT_MODE, getSecretKey(), IvParameterSpec(iv))
                }
            String(cipher.doFinal(encrypted), StandardCharsets.UTF_8)
        } catch (e: Exception) {
            when (e) {
                is NoSuchAlgorithmException,
                is NoSuchPaddingException -> throw RuntimeException("Failed to get an instance of Cipher", e)
                is BadPaddingException,
                is IllegalBlockSizeException ->
                    throw RuntimeException("Failed to encrypt the data with the generated key. Retry the purchase")
                else -> throw e
            }
        }

    private fun setupKeyStore(): KeyStore =
        try {
            KeyStore.getInstance(KEY_STORE)
                .apply { load(null) }
        } catch (e: KeyStoreException) {
            throw RuntimeException("Failed to get an instance of KeyStore", e)
        }

    private fun setupKeyGenerator(): KeyGenerator =
        try {
            KeyGenerator.getInstance(
                KEY_ALGORITHM_AES,
                KEY_STORE
            )
        } catch (e: Exception) {
            when (e) {
                is NoSuchAlgorithmException,
                is NoSuchProviderException ->
                    throw RuntimeException("Failed to get an instance of KeyGenerator", e)
                else -> throw e
            }
        }

    private fun getSecretKey(): SecretKey =
        try {
            if (!keyStore.containsAlias(KEY_ALIAS)) {
                keyGenerator.run {
                    val aesSpec =
                        KeyGenParameterSpec.Builder(KEY_ALIAS, PURPOSE_ENCRYPT or PURPOSE_DECRYPT)
                            .setBlockModes(BLOCK_MODE_CBC)
                            .setEncryptionPaddings(ENCRYPTION_PADDING_PKCS7)
                            .build()
                    init(aesSpec)
                    generateKey()
                }
            }
            keyStore.getKey(KEY_ALIAS, null) as SecretKey
        } catch (e: Exception) {
            when (e) {
                is NoSuchAlgorithmException,
                is InvalidAlgorithmParameterException,
                is CertificateException,
                is IOException -> throw RuntimeException(e)
                else -> throw e
            }
        }
}