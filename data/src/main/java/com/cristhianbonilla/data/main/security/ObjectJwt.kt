package com.cristhianbonilla.data.main.security

import android.os.Build
import android.security.keystore.KeyPermanentlyInvalidatedException
import android.util.Base64
import androidx.annotation.RequiresApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type
import java.security.KeyStoreException
import java.security.Signature
import java.security.SignatureException

class ObjectJwt {

    private val header = HashMap<String, String>()
    private val body = HashMap<String, String>()
    private var signatureString: String? = null
    private val moshi by lazy { Moshi.Builder().build() }
    private var map: Type = Types.newParameterizedType(
        MutableMap::class.java,
        String::class.java,
        String::class.java
    )

    companion object {
        fun createForEC521() = ObjectJwt().apply {
            header["alg"] = "ES512"
            header["typ"] = "JWT"

        }

        fun createForEC256() = ObjectJwt().apply {
            header["alg"] = "ES256"
            header["typ"] = "JWT"
        }
    }

    private fun headerJson(): String = moshi.adapter<Map<String, String>>(map).toJson(header)
    private fun bodyJson(): String = moshi.adapter<Map<String, String>>(map).toJson(body)
    private fun headerEncoded() = headerJson().toByteArray(Charsets.UTF_8).base64UrlEncode()
    private fun bodyEncoded()  =  bodyJson().toByteArray(Charsets.UTF_8).base64UrlEncode()
    private fun signatureEncoded() = signatureString

    fun addClaim(key: String, value: String){
        body[key] = value
    }

    fun addHeader(key: String, value: String){
        header[key] = value
    }

    fun getChallenge(): String {
        val header = headerEncoded()
        val body = bodyEncoded()
        val signature = signatureEncoded()
        var toSign  = "$header.$body"
        signature?.let { toSign = "$toSign.$signature" }
        return toSign
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun sign(signature: Signature): String? {
        val toSign  = getChallenge()
        return try {
            signature.update(toSign.toByteArray(Charsets.UTF_8))
            val signatureBytes = signature.sign()
            val rsByteArrayLength = ECDSAA.getSignatureByteArrayLength(this.header["alg"]!!)
            val jwsSignature = ECDSAA.transcodeSignatureToConcat(signatureBytes, rsByteArrayLength)
            val jwsSignedStr = jwsSignature.base64UrlEncode()
            signatureString = jwsSignedStr
            jwsSignedStr
        } catch (e: KeyPermanentlyInvalidatedException){
            null
        } catch (e: KeyStoreException){
            null
        } catch (e: SignatureException){
            null
        }
    }

    private fun ByteArray.base64UrlEncode(): String = Base64.encodeToString(this,Base64.URL_SAFE or Base64.NO_WRAP)

}