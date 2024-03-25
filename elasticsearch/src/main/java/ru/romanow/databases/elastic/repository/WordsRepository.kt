package ru.romanow.databases.elastic.repository

import org.springframework.data.repository.CrudRepository
import ru.romanow.databases.elastic.entity.Words

interface WordsRepository : CrudRepository<Words, String>
