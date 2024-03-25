package ru.romanow.databases.postgres.repository

import org.springframework.data.repository.CrudRepository
import ru.romanow.databases.postgres.entity.Words

interface WordsRepository : CrudRepository<Words, String>
