package com.project.postservice.transformer

import com.project.postservice.model.Event
import com.project.postservice.model.EventDto
import org.springframework.stereotype.Component

@Component
class EventTransformer {
    fun toDto(event: Event): EventDto {
        return EventDto(event.getId()!!, event.content, event.authorId, event.createdDate)
    }
}