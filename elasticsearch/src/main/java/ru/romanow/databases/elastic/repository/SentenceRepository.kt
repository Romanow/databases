package ru.romanow.databases.elastic.repository

import org.springframework.data.repository.CrudRepository
import ru.romanow.databases.elastic.entity.Sentences

interface SentenceRepository : CrudRepository<Sentences, String>
