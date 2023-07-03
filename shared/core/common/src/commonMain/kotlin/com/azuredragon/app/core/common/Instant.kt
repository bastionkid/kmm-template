package com.azuredragon.app.core.common

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun Instant.toLocalDate(): LocalDate {
    return toLocalDateTime(TimeZone.currentSystemDefault()).date
}

fun Instant.toLocalDateString(): String {
    val date = toLocalDate()

    return "${date.dayOfMonth} ${date.month.name}, ${date.year}"
}