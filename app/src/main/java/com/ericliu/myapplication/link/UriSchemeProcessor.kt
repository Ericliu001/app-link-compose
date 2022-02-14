package com.ericliu.myapplication.link

import android.net.Uri

class UriSchemeProcessor(
    val appLinkAuthorityProcessor: AppLinkAuthorityProcessor,
    val deeplinkAuthorityProcessor: DeeplinkAuthorityProcessor
) :
    UriProcessor {
    override fun process(uri: Uri) {
        val processor = when (uri.scheme?.lowercase()) {
            "http" ->
                appLinkAuthorityProcessor
            "https" ->
                appLinkAuthorityProcessor
            "google" ->
                deeplinkAuthorityProcessor
            else ->
                null
        }
        processor?.process(uri = uri)
    }
}