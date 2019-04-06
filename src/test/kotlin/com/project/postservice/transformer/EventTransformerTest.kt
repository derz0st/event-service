package com.project.postservice.transformer

import com.project.postservice.configuration.DateTimeProvider
import com.project.postservice.model.CreateEventRequest
import com.project.postservice.model.Event
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when` as on
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
internal class EventTransformerTest {
    @Mock
    private lateinit var dateTimeProvider: DateTimeProvider
    @InjectMocks
    private lateinit var subject: EventTransformer
    
    @Test
    internal fun toDto_transformsEntityToDto() {
        val dateTime = LocalDateTime.of(2019, 3, 5, 1, 4)
        val expected = Event("Content", 21, dateTime)
        expected.setId("event-id") 

        val actual = subject.toDto(expected)
        
        assertThat(actual.id).isEqualTo(expected.getId())
        assertThat(actual.authorId).isEqualTo(expected.authorId)
        assertThat(actual.content).isEqualTo(expected.content)
        assertThat(actual.createdDate).isEqualTo(expected.createdDate)
    }

    @Test
    internal fun toEntity_transformsRequestToEntity() {
        val createdDate = LocalDateTime.of(2019, 3, 5, 1, 4)
        val expected = CreateEventRequest("Content", 21)
        
        on(dateTimeProvider.now()).thenReturn(createdDate)

        val actual = subject.toEntity(expected)

        assertThat(actual.authorId).isEqualTo(expected.authorId)
        assertThat(actual.content).isEqualTo(expected.content)
        assertThat(actual.createdDate).isEqualTo(createdDate)
    }
    
}