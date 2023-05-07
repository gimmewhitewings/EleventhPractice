package com.example.eleventhpractice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.eleventhpractice.R
import com.example.eleventhpractice.databinding.FragmentWebViewBinding
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WebViewFragment : Fragment() {

    private lateinit var binding: FragmentWebViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWebViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            loadButton.setOnClickListener {
                Thread {
                    loadButton.post { loadButton.text = getString(R.string.loading) }
                    val content = getContent("https://developer.android.com/")
                    webView.post {
                        webView.loadDataWithBaseURL(
                            "https://developer.android.com/",
                            content,
                            "text/html",
                            "UTF-8",
                            "https://developer.android.com/"
                        )
                    }
                    loadButton.post { loadButton.text = getString(R.string.done) }
                }.start()
            }
        }
    }

    private fun getContent(path: String): String {
        var reader: BufferedReader? = null
        var stream: InputStream? = null
        var connection: HttpsURLConnection? = null
        try {
            val url = URL(path)
            connection = url.openConnection() as HttpsURLConnection
            connection.apply {
                requestMethod = "GET"
                readTimeout = 10000
                connect()
            }
            stream = connection.inputStream
            reader = BufferedReader(InputStreamReader(stream))
            val buf: StringBuilder = StringBuilder()
            var line: String? = reader.readLine()
            while (line != null) {
                buf.append(line).append("\n")
                line = reader.readLine()
            }
            return buf.toString()
        } finally {
            reader?.close()
            stream?.close()
            connection?.disconnect()
        }
    }
}