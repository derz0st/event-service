package com.project.postservice.controller

import com.project.postservice.model.CreateEventRequest
import com.project.postservice.model.EventDto
import com.project.postservice.service.EventService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/events")
class EventController(val eventService: EventService) {
    
    @PostMapping
    fun createEvent(@RequestBody createEventRequest: CreateEventRequest): EventDto = 
            eventService.createEvent(createEventRequest)

    @GetMapping("{id}")
    fun getEvent(@PathVariable id: String): EventDto =
            eventService.getEvent(id)
}