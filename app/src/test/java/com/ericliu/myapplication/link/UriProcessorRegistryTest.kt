package com.ericliu.myapplication.link

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class UriProcessorRegistryTest {
    private lateinit var uriProcessorRegistry: UriProcessorRegistry

    @Before
    fun setUp() {
        uriProcessorRegistry = UriProcessorRegistry(object : UriProcessorRegistry.Dependencies {
            override fun message(): MutableState<String> {
                return mutableStateOf("")
            }
        })
    }

    @Test
    fun testGetProcessorStandardLink() {
        val uri =
            Uri.parse("https://ericliu001.github.io/user?name=eric&title=employee&age=5")
        val processor = uriProcessorRegistry.getProcessor(uri = uri)
        assertNotNull(processor)
    }

    @Test
    fun isMatchWithExactPath() {
        val uri =
            Uri.parse("https://ericliu001.github.io/user/hello?name=eric&title=employee&age=5")

        val uriPattern = UriPattern(
            UriPattern.Scheme.HTTPS, UriPattern.Authority
                .ERICLIU001_GITHUB_IO, UriPattern.Path(pathExact = "/user/hello")
        )

        assertTrue(uriProcessorRegistry.isMatch(uri, uriPattern))
    }

    @Test
    fun isMatchWithPathPrefix() {
        val uri =
            Uri.parse("https://ericliu001.github.io/user/hello?name=eric&title=employee&age=5")

        val uriPattern = UriPattern(
            UriPattern.Scheme.HTTPS, UriPattern.Authority
                .ERICLIU001_GITHUB_IO, UriPattern.Path(pathPrefix = "/user")
        )
        assertTrue(uriProcessorRegistry.isMatch(uri = uri, uriPattern))
    }

    @Test
    fun isMatchWithWrongPathPrefix() {
        val uri =
            Uri.parse("https://ericliu001.github.io/user/hello?name=eric&title=employee&age=5")

        val uriPattern = UriPattern(
            UriPattern.Scheme.HTTPS, UriPattern.Authority
                .ERICLIU001_GITHUB_IO, UriPattern.Path(pathPrefix = "/good")
        )
        assertFalse(uriProcessorRegistry.isMatch(uri = uri, uriPattern))
    }


    @Test
    fun isMatchWithPathPattern() {
        val uri = Uri.parse("https://ericliu001.github.io/user/hello/world")
        val uriPattern = UriPattern(
            UriPattern.Scheme.HTTPS, UriPattern.Authority
                .ERICLIU001_GITHUB_IO, UriPattern.Path(pathPattern = ".*\\/hello\\/.*")
        )
        assertTrue(uriProcessorRegistry.isMatch(uri = uri, uriPattern))
    }

}