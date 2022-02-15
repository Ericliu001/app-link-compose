package com.ericliu.myapplication.link

import android.net.Uri

interface UriProcessor {
    fun uriPattern(): UriPattern

    fun process(uri: Uri)

    fun parseQueryParams(uri: Uri): Map<String, String> {
        val result = mutableMapOf<String, String>()
        for (queryParameterName in uri.queryParameterNames) {
            result[queryParameterName] = uri.getQueryParameter(queryParameterName) ?: ""
        }
        return result
    }

}