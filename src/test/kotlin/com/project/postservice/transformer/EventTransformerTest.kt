package com.project.postservice.transformer

import com.project.postservice.configuration.DateTimeProvider
import com.project.postservice.model.Event
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime
import javax.security.auth.Subject

@ExtendWith(MockitoExtension::class)
internal class EventTransformerTest {
    @Mock
    private lateinit var dateTimeProvider: DateTimeProvider
    @InjectMocks
    private lateinit var subject: EventTransformer
    
    @Test
    internal fun name() {
        val dateTime = LocalDateTime.of(2019, 3, 5, 1, 4)
        val expected = Event("Content", 21, dateTime)
        expected.setId("event-id") 

        val actual = subject.toDto(expected)
        
        assertThat(actual.id).isEqualTo(expected.getId())
        assertThat(actual.authorId).isEqualTo(expected.authorId)
        assertThat(actual.content).isEqualTo(expected.content)
        assertThat(actual.createdDate).isEqualTo(expected.createdDate)
    }
}