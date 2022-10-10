package ru.romanow.databases.redis

import org.springframework.data.redis.core.HashOperations
import org.springframework.stereotype.Service
import ru.romanow.databases.common.services.OperationProvider
import javax.annotation.Resource

@Service
class RedisOperationProvider : OperationProvider {

    @Resource(name = "stringRedisTemplate")
    private lateinit var hashOps: HashOperations<String, String, Int>

    override fun insertWord(word: String) {
        hashOps.increment("books", word, 1L)
    }
}