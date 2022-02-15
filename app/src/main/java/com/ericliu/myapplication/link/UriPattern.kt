package com.ericliu.myapplication.link

data class UriPattern(
    val scheme: Set<Scheme>,
    val authority: Authority,
    val path: Path = Path()
) {


    enum class Scheme(val scheme: String) {
        HTTP("http"),
        HTTPS("https"),
    }

    enum class Authority(val authority: String) {
        GITHUB("ericliu001.github.io")
    }

    data class Path(
        val path: String = "",
        val pathPrefix: String = "",
        val pathPattern: String = ""
    )
}


