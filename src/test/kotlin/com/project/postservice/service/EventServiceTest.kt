package com.project.postservice.service

import com.project.postservice.configuration.DateTimeProvider
import com.project.postservice.model.CreateEventRequest
import com.project.postservice.model.Event
import com.project.postservice.repository.EventRepository
import com.project.postservice.transformer.EventTransformer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Spy
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDateTime
import org.mockito.Mockito.`when` as on

@ExtendWith(MockitoExtension::class)
internal class EventServiceTest {
    private val CONTENT = "content"
    private val EVENT_ID = "event-id"
    private val AUTHOR_ID = 1L
    @Mock
    private lateinit var eventRepository: EventRepository
    @Mock
    private lateinit var dateTimeProvider: DateTimeProvider
    @Spy
    private lateinit var eventTransformer: EventTransformer
    @InjectMocks
    private lateinit var subject: EventService
    private val captor = ArgumentCaptor.forClass(Event::class.java)
    
    @Test
    internal fun createEvent_fillDateAndCallRepository() {
        val dateTime = LocalDateTime.now()
        val expected = Event(CONTENT, AUTHOR_ID, dateTime)
        expected.setId(EVENT_ID)

        on(dateTimeProvider.now()).thenReturn(dateTime)
        on(eventRepository.save(any<Event>())).then { expected }
        
        val request = CreateEventRequest(CONTENT, AUTHOR_ID)
        subject.createEvent(request)
        
        verify(eventRepository).save(captor.capture())
        
        val eventToSave = captor.value
        assertThat(eventToSave.getId()).isNull()
        assertThat(eventToSave.authorId).isEqualTo(expected.authorId)
        assertThat(eventToSave.content).isEqualTo(expected.content)
        assertThat(eventToSave.createdDate).isEqualTo(expected.createdDate)
    }

    @Test
    internal fun createEvent_returnsRepositoryReturnedResult() {
        val dateTime = LocalDateTime.now()
        val expected = Event(CONTENT, AUTHOR_ID, dateTime)
        expected.setId(EVENT_ID)

        on(dateTimeProvider.now()).thenReturn(dateTime)
        on(eventRepository.save(any<Event>())).then { expected }

        val actual = subject.createEvent(CreateEventRequest(CONTENT, AUTHOR_ID))
        
        assertThat(actual).isNotNull
        assertThat(actual.id).isEqualTo(expected.getId())
        assertThat(actual.authorId).isEqualTo(expected.authorId)
        assertThat(actual.content).isEqualTo(expected.content)
        assertThat(actual.createdDate).isEqualTo(expected.createdDate)
    }
}