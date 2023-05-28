package com.konkua.fimo

import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class MyHttpHandler {
    private var sb: StringBuilder? = null
    fun getRespose(url: String?): String {
        var urlConnection: HttpURLConnection? = null
        return try {
            val myUrl = URL(url)
            urlConnection = myUrl.openConnection() as HttpURLConnection
            urlConnection.readTimeout = 15000
            urlConnection!!.connectTimeout = 15000
            val `in`: InputStream = BufferedInputStream(urlConnection.inputStream)
            readStream(`in`)
        } catch (e: MalformedURLException) {
            throw RuntimeException(e)
        } catch (e: IOException) {
            throw RuntimeException(e)
        } finally {
            urlConnection!!.disconnect()
        }
    }

    @Throws(IOException::class)
    fun readStream1(`in`: InputStream): String {
        var result = ""
        val reader = BufferedReader(InputStreamReader(`in`, "UTF-8"), 8)
        sb = StringBuilder()
        var line = "0"
        while (reader.readLine().also { line = it } != null) {
            sb!!.append(
                """
    $line
    
    """.trimIndent()
            )
        }
        `in`.close()
        result = sb.toString()
        return result
    }

    fun readStream(`in`: InputStream?): String {
//        InputStream in = url.openStream();
        val reader = BufferedReader(InputStreamReader(`in`))
        val result = StringBuilder()
        var line: String?
        while (true) {
            try {
                if (reader.readLine().also { line = it } == null) break
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
            result.append(line)
        }
        return result.toString()
    }
}