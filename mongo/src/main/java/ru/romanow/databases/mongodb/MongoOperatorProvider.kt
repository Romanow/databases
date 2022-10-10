package ru.romanow.databases.mongodb

import org.springframework.data.mongodb.core.MongoTemplate
import ru.romanow.databases.common.services.OperationProvider

class MongoOperatorProvider(
    private val mongoTemplate: MongoTemplate,
) : OperationProvider {

    override fun insertWord(word: String) {
        mongoTemplate
    }
}