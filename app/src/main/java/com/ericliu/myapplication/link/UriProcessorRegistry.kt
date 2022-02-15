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
                uri.authority?.let { authority ->
                    pattern.authority.authority == authority
                } ?: false

            if (isAuthorityMatch) {
                isMatched =
                    if (pattern.path.path.isEmpty() &&
                        pattern.path.pathPattern.isEmpty() &&
                        pattern.path.pathPattern.isEmpty()
                    ) {
                        true
                    } else {

                        uri.path?.let { path ->
                            pattern.path.path == path ||
                                    path.startsWith(pattern.path.pathPrefix) ||
                                    path.matches(Regex(pattern.path.pathPattern))
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