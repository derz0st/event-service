package com.project.postservice.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

object ExceptionFactory {
    fun createEventNotFoundException(eventId: String) =
        EventNotFoundException("Event with id: $eventId not found")
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class EventNotFoundException(message: String?): RuntimeException(message)