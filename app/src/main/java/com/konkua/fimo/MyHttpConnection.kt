package com.konkua.fimo

import android.util.Log
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MyHttpConnection {

    fun getRespone(urlString:String): String {
        val url = URL(urlString)
        val httpClient = url.openConnection() as HttpURLConnection
        httpClient.readTimeout = 15000
        httpClient.connectTimeout = 15000
        if (httpClient.responseCode == HttpURLConnection.HTTP_OK) {
            try {
                val stream = BufferedInputStream(httpClient.inputStream)
                val data: String = readStream(inputStream = stream)
                Log.d("dataHttp======>",data)
                return data
            } catch (e: Exception) {
                getRespone(urlString)
            } finally {
                httpClient.disconnect()
            }
        } else {
            getRespone(urlString)
        }
        return ""
    }
    fun readStream(inputStream: BufferedInputStream): String {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        bufferedReader.forEachLine { stringBuilder.append(it) }
        return stringBuilder.toString()
    }


}