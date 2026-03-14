package com.universall.appcore.serialization

import com.universall.appcore.serialization.serializers.InstantToLocalDateSerializer
import com.universall.appcore.serialization.serializers.InstantToLocalDateTimeSerializer
import com.universall.appcore.serialization.serializers.InstantToLocalTimeSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

object CoreJsonHolder {
    val json = Json {
        serializersModule = SerializersModule {
            contextual(LocalDate::class, InstantToLocalDateSerializer)
            contextual(LocalDateTime::class, InstantToLocalDateTimeSerializer)
            contextual(LocalTime::class, InstantToLocalTimeSerializer)
        }

        isLenient = true
        explicitNulls = true
        encodeDefaults = true
        ignoreUnknownKeys = true
    }
}
