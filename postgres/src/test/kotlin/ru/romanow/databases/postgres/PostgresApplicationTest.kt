package ru.romanow.databases.postgres

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.PostgreSQLContainer

typealias PostgresContainer = PostgreSQLContainer<*>

@ActiveProfiles("test")
@SpringBootTest
class PostgresApplicationTest {

    @Test
    fun test() {
    }

    @TestConfiguration
    internal class PostgresTestConfiguration {

        @Bean
        @ServiceConnection
        fun postgres(): PostgreSQLContainer<*> {
            return PostgresContainer(POSTGRES_IMAGE)
                .withUsername(USERNAME)
                .withPassword(PASSWORD)
                .withDatabaseName(DATABASE)
        }

        companion object {
            private const val POSTGRES_IMAGE = "postgres:15"
            private const val DATABASE = "services"
            private const val USERNAME = "program"
            private const val PASSWORD = "test"
        }
    }

}
