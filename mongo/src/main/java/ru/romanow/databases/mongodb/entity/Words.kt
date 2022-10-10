package ru.romanow.databases.mongodb.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("words")
data class Words(
    @Id
    var word: String,
    var counter: Int,
)