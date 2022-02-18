package com.ericliu.myapplication.link

data class UriPattern(
    val scheme: Scheme,
    val authority: Authority,
    val path: Path = Path()
) {


    enum class Scheme(val value: String) {
        HTTP("http"),
        HTTPS("https"),
    }

    enum class Authority(val value: String) {
        ERICLIU001_GITHUB_IO("ericliu001.github.io")
    }

    data class Path(
        val pathExact: String? = null,
        val pathPrefix: String? = null,
        val pathPattern: String? = null
    )
}


