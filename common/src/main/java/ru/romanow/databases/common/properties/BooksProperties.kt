package ru.romanow.databases.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("books")
data class BooksProperties(
    var location: String
)