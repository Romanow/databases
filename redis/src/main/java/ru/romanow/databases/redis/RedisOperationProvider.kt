package ru.romanow.databases.redis

import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import ru.romanow.databases.common.services.OperationProvider

@Service
class RedisOperationProvider(redisTemplate: RedisTemplate<String, String>) : OperationProvider {
    private val hashOps: HashOperations<String, String, Int>

    init {
        hashOps = redisTemplate.opsForHash()
    }

    override fun insert(word: String) {
        hashOps.increment("books", word, 1L)
    }
}
