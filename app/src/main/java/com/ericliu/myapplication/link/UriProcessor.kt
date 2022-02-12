package com.ericliu.myapplication.link

import android.net.Uri

interface UriProcessor {
    fun process(uri: Uri)
}