package com.ericliu.myapplication.link

import android.net.Uri
import androidx.compose.runtime.MutableState
import com.ericliu.myapplication.link.UriPattern.Authority
import com.ericliu.myapplication.link.UriPattern.Path
import com.ericliu.myapplication.link.UriPattern.Scheme.HTTPS

class GithubUriProcessor(val message: MutableState<String>) : UriProcessor {
    override fun uriPattern(): UriPattern {
        return UriPattern(
            listOf(HTTPS),
            Authority.GITHUB,
            Path(path = "user")
        )
    }

    override fun process(uri: Uri) {
        uri.lastPathSegment?.let {
            message.value = it
        }
    }
}