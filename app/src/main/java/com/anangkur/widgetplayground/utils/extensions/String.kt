package com.anangkur.widgetplayground.utils.extensions

fun String.normalizeLink(): String {
    return if (contains("https://") || contains("http://")) {
        this
    } else {
        "https://$this"
    }
}

fun String?.findAllLinks(): List<String> {
    val regexPattern = Regex(pattern = """(?:(?:https?|ftp):\/\/)?[\w/\-?=%.]+\.[\w/\-&?=%.]+""")
    return findWithRegex(regexPattern)
}

fun String?.findAllUsernames(): List<String> {
    val regexPattern = Regex(pattern = """@?(?:[a-zA-Z0-9][a-zA-Z0-9._]*)?""")
    return findWithRegex(regexPattern)
}

fun String?.findWithRegex(regex: Regex): List<String> {
    return regex.findAll(this.orEmpty()).map { it.value }.toList()
}

fun List<String>.filterUserName(query: String): List<String> {
    return filter { it.contains(query) }
}