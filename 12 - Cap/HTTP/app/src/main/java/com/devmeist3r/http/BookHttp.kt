package com.devmeist3r.http

import android.content.Context
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

object BookHttp {
    val BOOK_JSON_URL = "https://raw.githubusercontent.com/nglauber/"+"dominando_android3/livros_novatec.json"

    @Throws(IOException::class)
    private fun connect(urlAddress: String): HttpURLConnection {
        val second = 1000
        val url = URL(urlAddress)
        val connection = (url.openConnection() as HttpURLConnection).apply {
            readTimeout = 10 * second
            connectTimeout = 15 * second
            requestMethod = "GET"
            doInput = true
            doOutput = false
        }
        connection.connect()
        return connection
    }

    fun hasConnection(ctx: Context): Boolean {

        return true
    }
}