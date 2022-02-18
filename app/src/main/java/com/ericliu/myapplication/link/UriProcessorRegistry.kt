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


    private fun getProcessors(): List<UriProcessor> {
        return listOf(GithubUriProcessor(dependencies.message()))
    }

    interface Dependencies {
        fun message(): MutableState<String>
    }
}