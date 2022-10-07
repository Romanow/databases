package ru.romanow.databases.redis

import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.core.HashOperations
import ru.romanow.databases.common.services.BookReader
import javax.annotation.Resource


@SpringBootApplication
class RedisApplication {

    private var logger = LoggerFactory.getLogger(RedisApplication::class.java)

    @Resource(name = "stringRedisTemplate")
    private lateinit var hashOps: HashOperations<String, String, Int>

    @Bean
    fun runner(bookReader: BookReader) = ApplicationRunner {
        val sentences = bookReader.readSentences()
        sentences
            .onEach {
                logger.info("Process sentence {}",
                    if (it.sentence.length > 20) it.sentence.take(20) + "..." else it.sentence)
            }
            .flatMap { it.words }
            .forEach {
                hashOps.increment("books", it, 1L)
            }
    }
}

fun main() {
    runApplication<RedisApplication>()
}
