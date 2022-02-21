package com.ericliu.myapplication.link

import android.net.Uri

class UriProcessorLocator(private val processors: List<UriProcessor>) {

    fun getProcessor(uri: Uri): UriProcessor? {
        return processors.find {
            val uriPattern = it.uriPattern()
            isMatch(uri, uriPattern)
        }
    }

    internal fun isMatch(uri: Uri, pattern: UriPattern): Boolean {
        var isMatched = false

        val isSchemeMatched = uri.scheme?.let { scheme ->
            pattern.scheme.value == scheme
        } ?: false

        if (isSchemeMatched) {
            val isAuthorityMatched =
                uri.authority?.let { authority ->
                    pattern.authority.value == authority
                } ?: false

            if (isAuthorityMatched) {
                isMatched = if (pattern.path.pathExact == null &&
                    pattern.path.pathPrefix == null &&
                    pattern.path.pathPattern == null
                ) {
                    true
                } else {
                    uri.path?.let { path ->
                        when {
                            pattern.path.pathExact != null -> path == pattern.path.pathExact
                            pattern.path.pathPrefix != null -> path.startsWith(pattern.path.pathPrefix)
                            pattern.path.pathPattern != null -> path.matches(
                                Regex(
                                    pattern.path.pathPattern
                                )
                            )
                            else -> false
                        }

                    } ?: false
                }
            }
        }

        return isMatched
    }
}