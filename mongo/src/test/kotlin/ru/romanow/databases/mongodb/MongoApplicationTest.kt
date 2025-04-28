package ru.romanow.databases.mongodb

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.MongoDBContainer

@ActiveProfiles("test")
@SpringBootTest
class MongoApplicationTest {

    @Test
    fun test() {
    }

    @TestConfiguration
    internal class MongoTestConfiguration {

        @Bean
        @ServiceConnection
        fun mongodb(): MongoDBContainer {
            return MongoDBContainer(MONGO_IMAGE)
                .withExposedPorts(EXPOSED_PORT)
        }

        companion object {
            private const val MONGO_IMAGE = "mongo:7"
            private const val EXPOSED_PORT = 27017
        }
    }

}
