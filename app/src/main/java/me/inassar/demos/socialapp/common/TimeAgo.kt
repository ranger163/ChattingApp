package me.inassar.demos.socialapp.common

import java.util.*

private const val SECOND_MILLIS = 1000
private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
private const val DAY_MILLIS = 24 * HOUR_MILLIS

private fun currentDate(): Date {
    val calendar = Calendar.getInstance()
    return calendar.time
}

fun getTimeAgo(timestamp: Long): String {
    val date = Date(timestamp)
    var time = date.time
    if (time < 1000000000000L) {
        time *= 1000
    }

    val now = currentDate().time
    if (time > now || time <= 0) {
        return "in the future"
    }

    val diff = now - time
    return when {
        diff < MINUTE_MILLIS -> "Seconds ago"
        diff < 2 * MINUTE_MILLIS -> "Minute ago"
        diff < 60 * MINUTE_MILLIS -> "${diff / MINUTE_MILLIS} minutes ago"
        diff < 2 * HOUR_MILLIS -> "Hour ago"
        diff < 24 * HOUR_MILLIS -> "${diff / HOUR_MILLIS} hours ago"
        diff < 48 * HOUR_MILLIS -> "Yesterday"
        else -> "${diff / DAY_MILLIS} days ago"
    }
}