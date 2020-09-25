package com.cristhianbonilla.data.main.security

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyPermanentlyInvalidatedException
import android.security.keystore.KeyProperties
import android.util.Base64
import androidx.annotation.RequiresApi
import com.cristhianbonilla.data.source.secure.SecureKeyPreferences
import com.cristhianbonilla.data.source.secure.SecureLocalSource
import com.cristhianbonilla.domain.exception.Failure
import com.cristhianbonilla.domain.security.ProviderSecurity
import com.cristhianbonilla.domain.functional.CustomResult
import com.cristhianbonilla.domain.functional.CustomResult.Error
import com.cristhianbonilla.domain.functional.getOrNull
import java.security.*
import java.security.spec.ECGenParameterSpec

class SecurityProviderImpl(
    private val secureLocalSource: SecureLocalSource
) :
    ProviderSecurity {

    companion object {
        private const val PROVIDER = "AndroidKeyStore"
        private const val CURVE = "secp256r1"
        private const val ALGORITHM = "SHA256withECDSA"
        private const val KEY_ALIAS_EC = "ECKeyAlias"
        private const val BASE64_FLAG = Base64.NO_WRAP
        private const val NONCE = "nonce"
    }

    override fun deleteKeyPair(): Boolean =
        try {
            getAndroidKeyStore().apply {
                if(containsAlias(KEY_ALIAS_EC)) {
                    deleteEntry(KEY_ALIAS_EC)
                }
            }
            true
        } catch (e: Exception) {
            false
        }

    override fun getPublicKeyBase64(): String? =
        getPublicKey()?.let {
            Base64.encodeToString(it.encoded, BASE64_FLAG)
        }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun getSignature(): CustomResult<Failure.SecurityError, Signature> {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return Error(Failure.SecurityError.NotSupportedError)

        }
        return try {
            val signature = Signature.getInstance(ALGORITHM)
            signature.initSign(getPrivateKey())
            CustomResult.Success(signature)
        } catch (key: KeyPermanentlyInvalidatedException) {
            Error(Failure.SecurityError.KeyPermanentlyInvalidatedError)
        } catch (e: Exception) {
            Error(Failure.SecurityError.UnknownError)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun signData(signature: Signature, data: String): String? =
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                signature.update(data.toByteArray())
                val bytes = signature.sign()
                Base64.encodeToString(bytes, BASE64_FLAG)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun verify(encryptData: String, data: String): Boolean =
        try {
            val signature = Signature.getInstance(ALGORITHM)
            signature.initVerify(getPublicKey())
            signature.update(data.toByteArray())
            signature.verify(Base64.decode(encryptData, BASE64_FLAG))
        } catch (e: Exception) {
            false
        }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun generateSignedJWT(signature: Signature): String? {

        val nonce = secureLocalSource.getValue(SecureKeyPreferences.LOGIN_NONCE, String::class).getOrNull()
        var jwt: ObjectJwt? = null
        nonce?.let {
            jwt = ObjectJwt.createForEC256()
            jwt?.addClaim(NONCE, nonce)
            jwt?.sign(signature)
        }

        return jwt?.getChallenge()

    }

    private fun getAndroidKeyStore(): KeyStore = KeyStore.getInstance(PROVIDER).apply { load(null) }

    private fun generateKeyPair(): Boolean =
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                deleteKeyPair()
                val generator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_EC, PROVIDER)
                val builder = KeyGenParameterSpec.Builder(KEY_ALIAS_EC, KeyProperties.PURPOSE_SIGN)
                    .setAlgorithmParameterSpec(ECGenParameterSpec(CURVE))
                    .setDigests(KeyProperties.DIGEST_SHA256)
                    .setUserAuthenticationRequired(true)
                generator.initialize(builder.build())
                generator.genKeyPair()
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }

    private fun checkKeyPair() =
        if (!getAndroidKeyStore().containsAlias(KEY_ALIAS_EC)) {
            generateKeyPair()
        } else {
            true
        }

    private fun getPublicKey(): PublicKey? =
        if (checkKeyPair()) getAndroidKeyStore().getCertificate(KEY_ALIAS_EC)?.publicKey else null

    private fun getPrivateKey(): PrivateKey? =
        if (checkKeyPair()) getAndroidKeyStore().getKey(KEY_ALIAS_EC, null) as PrivateKey? else null

}