package ru.romanow.databases.elastic.entity

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import org.springframework.data.elasticsearch.annotations.Setting
import java.util.UUID

@Document(indexName = "sentences")
@Setting(replicas = 2, shards = 10)
data class Sentences(

    @Id
    var id: String = UUID.randomUUID().toString().replace("-", ""),

    @Field(analyzer = "standard", type = FieldType.Text)
    var sentence: String,
)
