package com.project.postservice.transformer

import com.project.postservice.model.Event
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime
import org.mockito.Mockito.`when` as on

@ExtendWith(SpringExtension::class)
internal class EventTransformerTest {
    private val subject = EventTransformer()
    
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
}