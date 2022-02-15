package com.ericliu.myapplication.link

import android.net.Uri
import androidx.compose.runtime.MutableState
import com.ericliu.myapplication.link.UriPattern.Authority.GITHUB
import com.ericliu.myapplication.link.UriPattern.Path
import com.ericliu.myapplication.link.UriPattern.Scheme.HTTP
import com.ericliu.myapplication.link.UriPattern.Scheme.HTTPS

class GithubUriProcessor(val message: MutableState<String>) : UriProcessor {
    override fun uriPattern(): UriPattern {
        return UriPattern(
            setOf(HTTPS, HTTP),
            GITHUB,
            Path(path = "user")
        )
    }

    override fun process(uri: Uri) {
        val sb = StringBuilder()
        val params = parseQueryParams(uri = uri)
        for (entry in params.entries) {
            sb.append("{${entry.key}: ${entry.value}}\n")
        }
        message.value = sb.toString()
    }
}