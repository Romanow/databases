package ru.romanow.databases.elastic

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchConnectionDetails
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.testcontainers.containers.output.Slf4jLogConsumer
import org.testcontainers.elasticsearch.ElasticsearchContainer

@ActiveProfiles("test")
@SpringBootTest
class ElasticSearchApplicationTest {

    @Test
    fun test() {
    }

    @TestConfiguration
    internal class ElasticSearchTestConfiguration {
        private val logger = LoggerFactory.getLogger(ElasticSearchTestConfiguration::class.java)

        @Bean
        @ServiceConnection
        fun elastic(registry: DynamicPropertyRegistry): ElasticsearchContainer {
            return ElasticsearchContainer(ELASTIC_IMAGE)
                .withExposedPorts(EXPOSED_PORT)
                .withEnv("xpack.security.enabled", "false")
                .withLogConsumer { Slf4jLogConsumer(logger) }
        }

        companion object {
            private const val ELASTIC_IMAGE = "elasticsearch:8.12.2"
            private const val EXPOSED_PORT = 9200
        }
    }
}
