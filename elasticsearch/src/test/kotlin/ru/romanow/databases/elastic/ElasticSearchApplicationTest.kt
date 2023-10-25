package ru.romanow.databases.elastic

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.elasticsearch.ElasticsearchContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import org.testcontainers.utility.DockerImageName.parse

@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
class ElasticSearchApplicationTest {

    @Test
    fun test() {
    }

    companion object {
        private const val ELASTIC_IMAGE = "bitnami/elasticsearch:8.10.4"
        private const val USERNAME = "elastic"
        private const val PASSWORD = "qwerty"

        private val image: DockerImageName = parse(ELASTIC_IMAGE)
            .asCompatibleSubstituteFor("docker.elastic.co/elasticsearch/elasticsearch")

        @JvmStatic
        @Container
        var elastic: ElasticsearchContainer = ElasticsearchContainer(image)
            .withPassword(PASSWORD)
            .withEnv("xpack.security.transport.ssl.enabled", "false")
            .waitingFor(Wait.forHttp("/").forStatusCode(200))

        @JvmStatic
        @DynamicPropertySource
        fun registerProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.elasticsearch.uris") { elastic.httpHostAddress }
            registry.add("spring.elasticsearch.username") { USERNAME }
            registry.add("spring.elasticsearch.password") { PASSWORD }
        }
    }
}