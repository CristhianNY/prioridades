package com.cristhianbonilla.revistaprioridades.platform.security

import android.content.Context
import android.security.KeyPairGeneratorSpec
import android.util.Base64
import com.cristhianbonilla.data.main.security.Encryptar
import java.nio.charset.StandardCharsets.UTF_8
import javax.crypto.Cipher
import android.util.Base64.DEFAULT
import java.io.IOException
import java.math.BigInteger
import java.security.*
import java.security.cert.CertificateException
import java.util.*
import javax.crypto.BadPaddingException
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.security.auth.x500.X500Principal

private const val KEYSTORE_YEARS: Int = 25

class PSAEncryption(private val context: Context) : Encryptar {

    companion object {
        private const val KEY_STORE = "AndroidKeyStore"
        private const val TRANSFORMATION = "RSA/ECB/PKCS1Padding"
        private const val KEY_ALIAS = "aliasOK2"
        private const val SERIAL_NUMBER = 20200213L
    }

    private val keyStore: KeyStore by lazy { setupKeyStore() }

    init {
        generateKeyPair()
    }

    override fun encrypt(toEncrypt: String): String =
        try {
            val cipher = Cipher.getInstance(TRANSFORMATION)
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey())
            val cipherText =
                Base64.encodeToString(cipher.doFinal(toEncrypt.toByteArray(UTF_8)), DEFAULT)
            cipherText
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
            val encrypted = Base64.decode(toDecrypt, DEFAULT)
            val cipher = Cipher.getInstance(TRANSFORMATION)
                .apply {
                    init(Cipher.DECRYPT_MODE, getPrivateKey())
                }
            String(cipher.doFinal(encrypted), UTF_8)
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

    private fun generateKeyPair() {
        try {
            if (!keyStore.containsAlias(KEY_ALIAS)) {
                val startDate = Calendar.getInstance()
                val endDate = Calendar.getInstance()
                endDate.add(Calendar.YEAR, KEYSTORE_YEARS)
                val keyPairGeneratorSpec = KeyPairGeneratorSpec.Builder(context.applicationContext)
                    .setAlias(KEY_ALIAS)
                    .setSubject(X500Principal("CN=$KEY_ALIAS"))
                    .setSerialNumber(BigInteger.valueOf(SERIAL_NUMBER))
                    .setStartDate(startDate.time)
                    .setEndDate(endDate.time)
                    .build()
                val keyPairGenerator = KeyPairGenerator.getInstance("RSA", KEY_STORE)
                keyPairGenerator.initialize(keyPairGeneratorSpec)
                keyPairGenerator.generateKeyPair()
            }
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

    private fun getPublicKey(): PublicKey = keyStore.getCertificate(KEY_ALIAS).publicKey

    private fun getPrivateKey(): PrivateKey = keyStore.getKey(KEY_ALIAS, null) as PrivateKey
}