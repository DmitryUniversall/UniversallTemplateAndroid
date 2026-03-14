package com.universall.core.utils

import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.time.temporal.TemporalAdjusters
import java.util.Locale

data class TimeRange(
    val start: LocalTime,
    val end: LocalTime
)

fun LocalDate.weekBounds(
    firstDayOfWeek: DayOfWeek = DayOfWeek.MONDAY
): Pair<LocalDateTime, LocalDateTime> {
    val weekStart = this.with(TemporalAdjusters.previousOrSame(firstDayOfWeek)).atStartOfDay()
    val weekEnd = weekStart.plusDays(6).toLocalDate().atTime(LocalTime.MAX)
    return weekStart to weekEnd
}

fun LocalDate.timestampWeekBounds(): Pair<Long, Long> =
    this.weekBounds().let { (start, end) -> timestampRange(start, end) }

fun Long.toLocalDateTime(
    zone: ZoneId = ZoneId.systemDefault()
): LocalDateTime =
    LocalDateTime.ofInstant(Instant.ofEpochMilli(this), zone)

fun LocalDateTime.asTimestampWithDefaultZone(): Long =
    this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

fun LocalDateTime.isBeforeOrEqual(date: LocalDateTime): Boolean =
    this.isBefore(date) || this.isEqual(date)

fun LocalDateTime.isAfterOrEqual(date: LocalDateTime): Boolean =
    this.isAfter(date) || this.isEqual(date)

fun timestampRange(start: LocalDateTime, end: LocalDateTime): Pair<Long, Long> =
    start.asTimestampWithDefaultZone() to end.asTimestampWithDefaultZone();

fun LocalDate.dayBounds(): Pair<LocalDateTime, LocalDateTime> =
    this.atStartOfDay() to this.atTime(LocalTime.MAX)

fun LocalDate.timestampBounds(): Pair<Long, Long> =
    this.dayBounds().let { (start, end) -> timestampRange(start, end) }

fun DayOfWeek.twoLetterWeekDay(): String =
    this.getDisplayName(TextStyle.SHORT, Locale.ENGLISH).substring(0, 2)

fun LocalDate.twoLetterWeekDay(): String =
    this.dayOfWeek.twoLetterWeekDay()

fun LocalTime.toHHmm(): String =
    this.format(DateTimeFormatter.ofPattern("HH:mm"))

fun LocalDate.datesUntilInclusive(endDate: LocalDate): Sequence<LocalDate> =
    generateSequence(this) { date ->
        val next = date.plusDays(1)
        if (next.isAfter(endDate)) null else next
    }

infix fun LocalDate.until(end: LocalDate): Sequence<LocalDate> =
    generateSequence(this) { current ->
        current.plusDays(1).takeIf { it <= end }
    }
