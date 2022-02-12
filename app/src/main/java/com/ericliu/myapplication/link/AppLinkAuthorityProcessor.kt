package com.ericliu.myapplication.link

import android.net.Uri

class AppLinkAuthorityProcessor(val uriPathProcessor: UriPathProcessor) : UriProcessor {
    override fun process(uri: Uri) {
        val processor = when (uri.authority) {
            "ericliu001.github.io" -> {
                uriPathProcessor
            }
            else -> {
                null
            }
        }

        processor?.process(uri = uri)
    }
}