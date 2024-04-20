package com.freemimp.main.data

import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest

class AuthInterceptor(private val privateApiKey: String, private val publicApiKey: String) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url
        val timestamp = System.currentTimeMillis().toString()

        val dataToHash = "$timestamp$privateApiKey$publicApiKey"
        val hash = generateHash(dataToHash)

        val urlWithAuth = originalHttpUrl.newBuilder()
            .addQueryParameter("ts", timestamp)
            .addQueryParameter("apikey", publicApiKey)
            .addQueryParameter("hash", hash)
            .build()

        val requestWithAuth = originalRequest.newBuilder()
            .url(urlWithAuth)
            .build()

        return chain.proceed(requestWithAuth)
    }

    private fun generateHash(dataToHash: String): String {
        return dataToHash.md5()
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        val digest = md.digest(this.toByteArray())
        return digest.toHexString()
    }
}
