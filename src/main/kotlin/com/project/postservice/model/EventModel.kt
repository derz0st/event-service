package com.project.postservice.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.LocalDateTime
import java.time.ZonedDateTime

data class CreateEventRequest(
        @JsonProperty("content")
        val content: String,
        @JsonProperty("author_id")
        val authorId: Long
)

data class EventDto(
        @JsonProperty("id")
        val id: String,
        @JsonProperty("content")
        val content: String,
        @JsonProperty("author_id")
        val authorId: Long,
        @JsonProperty("created_date")
        val createdDate: LocalDateTime
)

@Document(collection = "events")
data class Event(
        @Field("content")
        val content: String,
        @Field("author_id")
        val authorId: Long,
        @Field("created_date")
        val createdDate: LocalDateTime
) : AbstractJpaPersistable()


