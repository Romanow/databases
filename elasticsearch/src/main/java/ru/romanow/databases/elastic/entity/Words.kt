package ru.romanow.databases.elastic.entity

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = "words")
data class Words(
    @Id
    var word: String,

    @Field(type = FieldType.Integer)
    var counter: Int,
)