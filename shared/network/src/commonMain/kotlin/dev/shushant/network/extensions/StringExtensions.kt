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
