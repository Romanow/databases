package ru.romanow.databases.elastic

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.testcontainers.elasticsearch.ElasticsearchContainer

@ActiveProfiles("test")
@SpringBootTest
class ElasticSearchApplicationTest {

    @Test
    fun test() {
    }

    @TestConfiguration
    internal class ElasticSearchTestConfiguration {

        @Bean
        @ServiceConnection
        fun elastic(registry: DynamicPropertyRegistry): ElasticsearchContainer {
            return ElasticsearchContainer(ELASTIC_IMAGE)
                .withExposedPorts(EXPOSED_PORT)
                .withEnv("xpack.security.enabled", "false")
        }

        companion object {
            private const val ELASTIC_IMAGE = "elasticsearch:8.12.2"
            private const val EXPOSED_PORT = 9200
        }
    }
}
