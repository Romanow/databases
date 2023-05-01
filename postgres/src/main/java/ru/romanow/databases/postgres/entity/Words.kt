package ru.romanow.databases.postgres.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "words")
data class Words(

    @Id
    @Column(name = "word", nullable = false)
    var word: String? = null,

    @Column(name = "counter")
    var counter: Int? = null,
)