package com.playpen.kafkaJsonDemo.util

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object DateUtil {

    fun nowISO8601(): String = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT)

    fun nowInUTC(): LocalDateTime = LocalDateTime.now(ZoneOffset.UTC)
}
