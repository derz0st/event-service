package com.project.postservice.service

import com.project.postservice.configuration.DateTimeProvider
import com.project.postservice.exception.ExceptionFactory
import com.project.postservice.model.CreateEventRequest
import com.project.postservice.model.Event
import com.project.postservice.model.EventDto
import com.project.postservice.repository.EventRepository
import com.project.postservice.transformer.EventTransformer
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class EventService(private val eventRepository: EventRepository,
                   private val eventTransformer: EventTransformer,
                   private val dateTimeProvider: DateTimeProvider) {
    private val logger = KotlinLogging.logger {}
    
    fun createEvent(eventRequest: CreateEventRequest): EventDto {
        logger.debug { "Going to persist event: $eventRequest" }
        val event = toEntity(eventRequest)
        
        val savedEvent = eventRepository.save(event)
        logger.info { "Event is created: $savedEvent" }
        
        return eventTransformer.toDto(savedEvent)
    }

    fun getEvent(id: String): EventDto {
        logger.debug { "Looking for the event with id: $id" }
        
        val event = eventRepository.findById(id).orElseThrow {
            logger.error { "Event with id: $id not found" }
            ExceptionFactory.createEventNotFoundException(id)
        }

        return eventTransformer.toDto(event)
    }
    
    private fun toEntity(request: CreateEventRequest) =
            Event(request.content, request.authorId, dateTimeProvider.now())
}