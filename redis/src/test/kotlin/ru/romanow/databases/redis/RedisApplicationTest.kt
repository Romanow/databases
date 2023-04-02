package ru.romanow.databases.redis

import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.ClientType
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

typealias RedisContainer = GenericContainer<*>

@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
class RedisApplicationTest {

    @Test
    fun test() {
    }

    companion object {
        private const val REDIS_IMAGE = "redis:7.0"
        private const val EXPOSED_PORT = 6379

        @JvmStatic
        @Container
        var redis: RedisContainer = RedisContainer(REDIS_IMAGE)
            .withExposedPorts(EXPOSED_PORT)

        @JvmStatic
        @DynamicPropertySource
        fun registerProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.redis.host") { "localhost" }
            registry.add("spring.data.redis.port") { redis.getMappedPort(EXPOSED_PORT) }
            registry.add("spring.data.redis.client-type") { ClientType.LETTUCE }
        }
    }
}