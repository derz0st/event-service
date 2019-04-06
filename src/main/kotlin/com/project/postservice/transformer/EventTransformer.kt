package com.project.postservice.transformer

import com.project.postservice.configuration.DateTimeProvider
import com.project.postservice.model.CreateEventRequest
import com.project.postservice.model.Event
import com.project.postservice.model.EventDto
import org.springframework.stereotype.Component

@Component
class EventTransformer(private val dateTimeProvider: DateTimeProvider) {
    fun toDto(event: Event): EventDto {
        return EventDto(event.getId()!!, event.content, event.authorId, event.createdDate)
    }

    fun toEntity(eventRequest: CreateEventRequest): Event {
        return Event(eventRequest.content, eventRequest.authorId, dateTimeProvider.now())
    }
}