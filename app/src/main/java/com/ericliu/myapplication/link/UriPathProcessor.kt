package com.ericliu.myapplication.link

import android.net.Uri
import androidx.compose.runtime.MutableState

class UriPathProcessor(val message: MutableState<String>) : UriProcessor {
    override fun process(uri: Uri) {
        uri.lastPathSegment?.let {
            message.value = it
        }
    }
}