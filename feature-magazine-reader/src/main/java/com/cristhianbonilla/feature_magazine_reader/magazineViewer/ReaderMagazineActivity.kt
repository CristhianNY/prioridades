package com.cristhianbonilla.feature_magazine_reader.magazineViewer

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.view.WindowManager
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.cristhianbonilla.feature_magazine_reader.R
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.util.FitPolicy
import kotlinx.android.synthetic.main.activity_reader_magazine.*
import kotlinx.coroutines.*
import java.io.InputStream
import java.net.URL


class ReaderMagazineActivity : AppCompatActivity(), OnLoadCompleteListener {
    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_reader_magazine)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val magazineUrl = intent.getStringExtra("URL_MAGAZINE")
        //  showPdfFile(magazineUrl.toString())
        magazineUrl?.let { loadData(it) }
    }

    @RequiresApi(Build.VERSION_CODES.FROYO)
    fun showPdfBase64(magazineUrl: String) {
        GlobalScope.launch {
            val input: InputStream = URL(magazineUrl).openStream()
            webView.fromBytes(input.readBytes())
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .pageSnap(true)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load()

        }
    }

    fun loadData(magazineUrl: String) = CoroutineScope(Dispatchers.Main).launch {
        val task = async(Dispatchers.IO) {
            GlobalScope.launch {
                val input: InputStream = URL(magazineUrl).openStream()
                webView.fromBytes(input.readBytes())
                    .enableSwipe(true) // allows to block changing pages using swipe
                    .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .defaultPage(0)
                    .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                    .password(null)
                    .scrollHandle(null)
                    .pageSnap(true)
                    .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                    // spacing between pages in dp. To define spacing color, set view background
                    .spacing(0)
                    .pageFitPolicy(FitPolicy.WIDTH)
                    .load()

            }
        }
        task.await()
        delay(4000)
        pb.visibility = View.GONE
        webView.visibility = View.VISIBLE

    }

    override fun loadComplete(nbPages: Int) {
        Toast.makeText(this, "cargo", Toast.LENGTH_LONG).show()
        // pb.visibility = View.GONE
    }

}