package com.ericliu.myapplication.link

import android.net.Uri
import androidx.compose.runtime.MutableState

class UriProcessorRegistry(val dependencies: Dependencies) {

    fun getProcessor(uri: Uri): UriProcessor? {
        return getProcessors().find {
            val uriPattern = it.uriPattern()
            isMatch(uri, uriPattern)
        }
    }

    private fun isMatch(uri: Uri, pattern: UriPattern): Boolean {
        var isMatched = false

        val isSchemeMatch = uri.scheme?.let { scheme ->
            pattern.scheme.any { it.scheme == scheme }
        } ?: false

        if (isSchemeMatch) {
            val isAuthorityMatch =
                uri.authority?.let {
                    pattern.authority.authority == it
                } ?: false

            if (isAuthorityMatch) {
                isMatched =
                    if (pattern.path.path.isEmpty() &&
                        pattern.path.pathPattern.isEmpty() &&
                        pattern.path.pathPattern.isEmpty()
                    ) {
                        true
                    } else {

                        uri.path?.let {
                            pattern.path.path == it || it.startsWith(pattern.path.pathPrefix) || it
                                .matches(Regex(pattern.path.pathPattern))
                        } ?: false
                    }
            }
        }

        return isMatched
    }


    private fun getProcessors(): List<UriProcessor> {
        return listOf(GithubUriProcessor(dependencies.message()))
    }

    interface Dependencies {
        fun message(): MutableState<String>
    }
}