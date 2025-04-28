package ru.romanow.databases.redis

import com.redis.testcontainers.RedisContainer
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest
class RedisApplicationTest {

    @Test
    fun test() {
    }

    @TestConfiguration
    internal class RedisTestConfiguration {

        @Bean
        @ServiceConnection
        fun redis(): RedisContainer {
            return RedisContainer(REDIS_IMAGE).withExposedPorts(EXPOSED_PORT)
        }

        companion object {
            private const val REDIS_IMAGE = "redis:7.2"
            private const val EXPOSED_PORT = 6379
        }
    }
}
