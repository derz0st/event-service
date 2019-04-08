package com.project.postservice.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
internal class EventModelKtTest {

    @Test
    internal fun toDto_transformsEntityToDto() {
        val dateTime = LocalDateTime.of(2019, 3, 5, 1, 4)
        val expected = Event("Content", 21, dateTime)
        expected.setId("event-id")

        val actual = expected.toDto()

        Assertions.assertThat(actual.id).isEqualTo(expected.getId())
        Assertions.assertThat(actual.authorId).isEqualTo(expected.authorId)
        Assertions.assertThat(actual.content).isEqualTo(expected.content)
        Assertions.assertThat(actual.createdDate).isEqualTo(expected.createdDate)
    }
}
