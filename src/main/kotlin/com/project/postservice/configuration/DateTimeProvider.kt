package com.project.postservice.configuration

import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class DateTimeProvider {
    fun now(): LocalDateTime = LocalDateTime.now()
}