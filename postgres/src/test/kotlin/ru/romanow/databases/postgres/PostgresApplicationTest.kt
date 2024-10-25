package ru.romanow.databases.postgres

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

typealias PostgresContainer = PostgreSQLContainer<*>

@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
class PostgresApplicationTest {

    @Test
    fun test() {
    }

    companion object {
        private const val POSTGRES_IMAGE = "postgres:15"
        private const val DATABASE = "services"
        private const val USERNAME = "program"
        private const val PASSWORD = "test"

        @JvmStatic
        @Container
        var postgres: PostgresContainer = PostgresContainer(POSTGRES_IMAGE)
            .withDatabaseName(DATABASE)
            .withUsername(USERNAME)
            .withPassword(PASSWORD)

        @JvmStatic
        @DynamicPropertySource
        fun registerProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url") { "jdbc:postgresql://localhost:${postgres.getMappedPort(5432)}/$DATABASE" }
            registry.add("spring.datasource.username") { USERNAME }
            registry.add("spring.datasource.password") { PASSWORD }
        }
    }

}
