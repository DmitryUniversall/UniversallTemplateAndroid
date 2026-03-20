package com.universall.core.utils

fun Throwable.messageOrDefault(defaultMessage: String): String = message ?: defaultMessage
