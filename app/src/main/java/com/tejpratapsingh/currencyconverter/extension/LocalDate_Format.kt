package com.tejpratapsingh.currencyconverter.extension

import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Format date to yyyy-MM-dd
 */
fun LocalDate.formatToApiDateStringFormat(): String {
    return format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}

/**
 * Format date from yyyy-MM-dd
 */
fun String.dateFromApiDateString(): LocalDate {
    return LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}

fun LocalDate.formatToDbDateStringFormat(): Int {
    return format(DateTimeFormatter.ofPattern("yyyyMMdd")).toInt()
}

fun Int.getDateFromDbDate(): LocalDate {
    return LocalDate.parse(this.toString(), DateTimeFormatter.ofPattern("yyyyMMdd"))
}