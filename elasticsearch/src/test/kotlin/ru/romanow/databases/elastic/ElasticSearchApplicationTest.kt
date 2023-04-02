package ru.romanow.databases.elastic

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.elasticsearch.ElasticsearchContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
class ElasticSearchApplicationTest {

    @Test
    fun test() {
    }

    companion object {
        private const val ELASTIC_IMAGE = "docker.elastic.co/elasticsearch/elasticsearch:8.6.2"
        private const val USERNAME = "elastic"
        private const val PASSWORD = "qwerty"

        @JvmStatic
        @Container
        var elastic: ElasticsearchContainer = ElasticsearchContainer(ELASTIC_IMAGE)
            .withPassword(PASSWORD)
            .withEnv("xpack.security.transport.ssl.enabled", "false")

        @JvmStatic
        @DynamicPropertySource
        fun registerProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.elasticsearch.uris") { elastic.httpHostAddress }
            registry.add("spring.elasticsearch.username") { USERNAME }
            registry.add("spring.elasticsearch.password") { PASSWORD }
        }
    }
}