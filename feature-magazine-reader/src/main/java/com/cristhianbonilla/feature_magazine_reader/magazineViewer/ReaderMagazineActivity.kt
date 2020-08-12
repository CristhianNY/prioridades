package com.cristhianbonilla.feature_magazine_reader.magazineViewer

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.cristhianbonilla.feature_magazine_reader.R
import kotlinx.android.synthetic.main.activity_reader_magazine.*


class ReaderMagazineActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_reader_magazine)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val magazineUrl = intent.getStringExtra("URL_MAGAZINE")
        showPdfFile(magazineUrl.toString())
    }

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressLint("SetJavaScriptEnabled")
    private fun showPdfFile(magazineUrl: String) {
    val filename =
            "https://docs.google.com/gview?embedded=true&url=$magazineUrl"
     //   val filename ="https://www.artistasamerica.com/"

        val idsToHide =
            arrayOf("ndfHFb-c4YZDc-Wrql6b")
        webView.settings.javaScriptEnabled = true
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url)

                if (view.title.equals("")){
                    view.reload()
                }else{
                    webView.settings.javaScriptEnabled = true

                    with(view.loadUrl("javascript:(function() { " +
                            "document.getElementsByClassName('ndfHFb-c4YZDc-Wrql6b')[0].style.display='none'; })()")){
                        webView.visibility = View.VISIBLE

                    }

                    pb.visibility = View.GONE

                }
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        webView.loadUrl(filename)

    }

}