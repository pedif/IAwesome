package com.techspark.iawesome.util

import java.util.*

enum class AwesomeTime {

    MORNING,
    DAYTIME,
    EVENING,
    NIGHTTIME;

    companion object {
        /**
         * Returns an enum based on current time
         */
        fun getTime(): AwesomeTime {
            return when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {

                in 6..8 -> MORNING
                in 9..19 -> DAYTIME
                in 20..22 -> EVENING
                else -> NIGHTTIME

            }
        }
    }
}