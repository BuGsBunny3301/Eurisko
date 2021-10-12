package org.smartmobiletech.euriskoapp

import java.util.concurrent.TimeUnit

object Utility {

    const val TIMER_INTERVAL = 1000

    fun getFormattedStopWatch(ms: Long): String {
        var milliseconds = ms
        val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
        milliseconds -= TimeUnit.HOURS.toMillis(hours)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
        milliseconds -= TimeUnit.MINUTES.toMillis(minutes)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)

        return "${if (hours < 10) "0" else ""}$hours" + "h" +
                "${if (minutes < 10) "0" else ""}$minutes" + "m" +
                "${if (seconds < 10) "0" else ""}$seconds" + "s"
    }
}