package ru.romanow.databases.mongodb

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service
import ru.romanow.databases.common.services.OperationProvider
import ru.romanow.databases.mongodb.entity.Words

@Service
class MongoOperatorProvider(
    private val mongoTemplate: MongoTemplate,
) : OperationProvider {

    override fun insertWord(word: String) {
        val query = Query.query(Criteria.where("word").`is`(word))
        val update = Update().inc("counter", 1)
        mongoTemplate.upsert(query, update, Words::class.java)
    }
}