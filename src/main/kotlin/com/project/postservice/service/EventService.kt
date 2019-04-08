package com.project.postservice.service

import com.project.postservice.configuration.DateTimeProvider
import com.project.postservice.exception.ExceptionFactory
import com.project.postservice.model.CreateEventRequest
import com.project.postservice.model.Event
import com.project.postservice.model.EventDto
import com.project.postservice.model.toDto
import com.project.postservice.repository.EventRepository
import mu.KotlinLogging
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class EventService(private val eventRepository: EventRepository,
                   private val dateTimeProvider: DateTimeProvider) {
    private val logger = KotlinLogging.logger {}

    fun createEvent(eventRequest: CreateEventRequest): EventDto {
        logger.debug { "Going to persist event: $eventRequest" }
        val event = toEntity(eventRequest)

        val savedEvent = eventRepository.save(event)
        logger.info { "Event is created: $savedEvent" }

        return savedEvent.toDto()
    }

    fun getEvent(id: String): EventDto {
        logger.debug { "Looking for the event with id: $id" }

        val event = eventRepository.findById(id).orElseThrow {
            logger.error { "Event with id: $id not found" }
            ExceptionFactory.createEventNotFoundException(id)
        }
        return event.toDto()
    }

    private fun toEntity(request: CreateEventRequest) =
            Event(request.content, request.authorId, dateTimeProvider.now())

    fun getAllEvents(pageable: Pageable): Page<EventDto> {
        val events = eventRepository.findAll(pageable)
        return events.map(Event::toDto)
    }
}
