package com.project.postservice.service

import com.project.postservice.model.CreateEventRequest
import com.project.postservice.model.Event
import com.project.postservice.repository.EventRepository
import com.project.postservice.transformer.EventTransformer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.AdditionalAnswers.returnsFirstArg
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Spy
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
internal class EventServiceTest {
    @Mock
    private lateinit var eventRepository: EventRepository
    @Mock
    private lateinit var eventTransformer: EventTransformer
    @InjectMocks
    private lateinit var subject: EventService

    @Test
    internal fun createEvent() {
        `when`(eventRepository.save(any<Event>())).then { returnsFirstArg<Event>() }
        
        val request = CreateEventRequest("content", 1)
        val actual = subject.createEvent(request)

        assertThat(actual).isNotNull

    }
}