package com.project.postservice.repository

import com.project.postservice.model.Event
import com.project.postservice.util.MongoInitializer
import mu.KotlinLogging
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@DataMongoTest
@ContextConfiguration(initializers = [MongoInitializer::class])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class EventRepositoryTest {
    @Autowired
    private lateinit var subject: EventRepository
    
    @Test
    internal fun save() {
        val dateTime = LocalDateTime.of(2018, 5, 29, 13, 54, 26)
        val saved = subject.save(Event("My text", 145, dateTime))
        
        //TODO: rewrite using mongo template instead of repository
        val actual = subject.findById(saved.getId()!!)

        assertThat(actual).isNotEmpty
        assertThat(actual.get().getId()!!).isNotEmpty()
        assertThat(actual.get()).isEqualToIgnoringGivenFields(saved, "id")
    }
}