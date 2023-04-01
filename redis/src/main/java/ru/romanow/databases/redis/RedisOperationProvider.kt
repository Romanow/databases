package ru.romanow.databases.redis

import jakarta.annotation.Resource
import org.springframework.data.redis.core.HashOperations
import org.springframework.stereotype.Service
import ru.romanow.databases.common.services.OperationProvider

@Service
class RedisOperationProvider(
    @Resource(name = "stringRedisTemplate")
    private var hashOps: HashOperations<String, String, Int>
) : OperationProvider {

    override fun insert(word: String) {
        hashOps.increment("books", word, 1L)
    }
}