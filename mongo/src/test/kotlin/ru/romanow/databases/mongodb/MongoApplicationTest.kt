package ru.romanow.databases.mongodb

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

typealias MongoContainer = GenericContainer<*>

@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
class MongoApplicationTest {

    @Test
    fun test() {
    }

    companion object {
        private const val MONGO_IMAGE = "mongo:6"
        private const val DATABASE = "testdb"
        private const val USERNAME = "program"
        private const val PASSWORD = "test"
        private const val EXPOSED_PORT = 27017

        @JvmStatic
        @Container
        var mongo: MongoContainer = MongoContainer(MONGO_IMAGE)
            .withEnv("MONGO_INITDB_ROOT_USERNAME", USERNAME)
            .withEnv("MONGO_INITDB_ROOT_PASSWORD", PASSWORD)
            .withExposedPorts(EXPOSED_PORT)

        @JvmStatic
        @DynamicPropertySource
        fun registerProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri") { "mongodb://localhost:${mongo.getMappedPort(EXPOSED_PORT)}/$DATABASE" }
            registry.add("spring.data.mongodb.username") { USERNAME }
            registry.add("spring.data.mongodb.password") { PASSWORD }
        }
    }
}
