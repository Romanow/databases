package ru.romanow.databases.mongodb

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
class MongoApplicationTest {

    @Test
    fun test() {
    }

    companion object {
        private const val MONGO_IMAGE = "mongo:6"

        @JvmStatic
        @Container
        var mongo: MongoDBContainer = MongoDBContainer(MONGO_IMAGE)

        @JvmStatic
        @DynamicPropertySource
        fun registerPgProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri") { mongo.replicaSetUrl }
        }
    }
}