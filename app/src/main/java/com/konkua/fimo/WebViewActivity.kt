package com.konkua.fimo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    var robocash: Robocash? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        webView = findViewById(R.id.webview)
        var url: String = intent.getStringExtra("LINK").toString()
        webView.settings.setJavaScriptEnabled(true)
        webView.settings.setSupportZoom(true)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, urlstr: String?): Boolean {
                view?.loadUrl(urlstr.toString())
                return true
            }
        }
        webView.loadUrl(url)


    }

    private inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            view.loadUrl(request.getUrl().toString())
            return true
        }
    }
}