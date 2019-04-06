package com.project.postservice.util

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext

class MongoInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    val mongoPort = 27017
    
    override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
        val mongo = KGenericContainer("mongo:latest").withExposedPorts(mongoPort)!!
        mongo.start()

        val port = mongo.getMappedPort(mongoPort)
        TestPropertyValues.of("spring.data.mongodb.port=$port")
                .applyTo(configurableApplicationContext.environment)
    }
}