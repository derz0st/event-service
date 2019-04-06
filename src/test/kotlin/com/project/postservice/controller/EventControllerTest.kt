package com.project.postservice.controller

import com.project.postservice.exception.ExceptionFactory
import com.project.postservice.model.EventDto
import com.project.postservice.service.EventService
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.mockito.Mockito.anyString
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime
import org.mockito.Mockito.`when` as on

@WebMvcTest(controllers = [EventController::class])
internal class EventControllerTest {
    @MockBean
    private lateinit var eventService: EventService
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    internal fun getEvent_whenEventExists_returnsIt() {
        val expected = EventDto(
                "event-id", 
                "content", 
                1, 
                LocalDateTime.parse("2019-05-02T04:14:12"))

        on(eventService.getEvent(anyString())).thenReturn(expected)
        
        mockMvc.perform(get("/api/events/event-id"))
                .andDo { result -> println(result) }
                .andExpect(status().isOk)
                .andExpect(jsonPath("id", equalTo("event-id")))
                .andExpect(jsonPath("content", equalTo("content")))
                .andExpect(jsonPath("author_id", equalTo(1)))
                .andExpect(jsonPath("created_date", equalTo("2019-05-02T04:14:12")))
        
        verify(eventService).getEvent("event-id")
    }

    @Test
    internal fun getEvent_whenEventDoesNotExist_returnsNotFound() {
        val notFoundException = ExceptionFactory.createEventNotFoundException("event-id")
        on(eventService.getEvent(anyString())).thenThrow(notFoundException)

        mockMvc.perform(get("/api/events/event-id"))
                .andDo { result -> println(result) }
                .andExpect(status().isNotFound)

        verify(eventService).getEvent("event-id")
    }
}