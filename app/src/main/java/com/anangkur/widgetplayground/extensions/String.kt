package com.anangkur.widgetplayground.extensions

fun String.normalizeLink(): String {
    return if (contains("https://") || contains("http://")) {
        this
    } else {
        "https://$this"
    }
}

fun String?.findAllLinks(): List<String> {
    val regexPattern = Regex(pattern = """(?:(?:https?|ftp):\/\/)?[\w/\-?=%.]+\.[\w/\-&?=%.]+""")
    return regexPattern.findAll(this.orEmpty()).map { it.value }.toList()
}