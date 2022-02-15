package com.ericliu.myapplication.link

import android.net.Uri

interface UriProcessor {
    fun uriPattern(): UriPattern

    fun process(uri: Uri)
}