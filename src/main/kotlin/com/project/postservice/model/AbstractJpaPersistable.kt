package com.project.postservice.model

import org.springframework.data.annotation.Id

abstract class AbstractJpaPersistable {
    @Id
    private var id: String? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id 
    }
}