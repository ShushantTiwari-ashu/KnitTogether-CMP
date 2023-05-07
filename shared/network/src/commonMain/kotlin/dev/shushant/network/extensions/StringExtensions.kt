package dev.shushant.network.extensions

import io.ktor.util.date.GMTDate
import kotlin.random.Random

fun String.toWordCaps(): String {
    val words = this.split(" ")

    var newStr = ""

    words.forEach {
        newStr += it.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() } + " "
    }
    return newStr.trim()
}

internal const val FIREBASE_WEB_API_KEY = ""

val hashtagPattern = Regex("(#[A-Za-z0-9_-]+)")
val linkPattern = Regex("(#[A-Za-z0-9_-]+)")
const val IS_LINK = "IS_LINK"

fun splitText(text: String): List<Triple<String, Boolean, String?>> {
    val segments = mutableListOf<Triple<String, Boolean, String?>>()
    var lastIndex = 0
    hashtagPattern.findAll(text).forEach { result ->
        val range = result.range
        if (lastIndex < range.first) {
            segments.add(Triple(text.substring(lastIndex, range.first), false, null))
        }
        segments.add(Triple(result.value, true, null))
        lastIndex = range.last + 1
    }
    linkPattern.findAll(text).forEach { result ->
        val range = result.range
        if (lastIndex < range.first) {
            //segments.add(Triple(text.substring(lastIndex, range.first), false, null))
        }
        segments.add(Triple(result.value, true, IS_LINK))
        lastIndex = range.last + 1
    }
    if (lastIndex < text.length) {
        segments.add(Triple(text.substring(lastIndex), false, null))
    }
    return segments
}

fun identifyLinksAndHashtags(text: String): Pair<List<String>, List<String>> {
    val links = Regex("""\b(?:https?://|www\.)\S+\b""").findAll(text)
        .map { it.value }.toList()
    val hashtags = Regex("""\B#\w\w+\b""").findAll(text)
        .map { it.value }.toList()
    return Pair(links, hashtags)
}

fun generateFullName(): String {
    val firstNames = listOf(
        "Emma",
        "Olivia",
        "Sophia",
        "Ava",
        "Isabella",
        "Mia",
        "Charlotte",
        "Amelia",
        "Harper",
        "Evelyn",
        "Abigail",
        "Emily",
        "Elizabeth",
        "Mila",
        "Ella"
    )
    val lastNames = listOf(
        "Smith",
        "Johnson",
        "Williams",
        "Jones",
        "Brown",
        "Garcia",
        "Miller",
        "Davis",
        "Rodriguez",
        "Martinez",
        "Wilson",
        "Anderson",
        "Thomas",
        "Jackson"
    )

    val firstName = firstNames.random()
    val lastName = lastNames.random()

    return "$firstName $lastName"
}

val generateUserId: String
    get() {
        val timestamp = GMTDate().timestamp
        val random = Random.nextInt(10000) // generate a random number between 0 and 9999
        return "$timestamp-$random"
    }
