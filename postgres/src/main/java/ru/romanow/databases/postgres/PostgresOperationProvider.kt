package ru.romanow.databases.postgres

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import ru.romanow.databases.common.services.OperationProvider
import ru.romanow.databases.postgres.entity.Words
import ru.romanow.databases.postgres.repository.WordsRepository

@Service
class PostgresOperationProvider(
    private val wordsRepository: WordsRepository
) : OperationProvider {

    @Transactional
    override fun insert(word: String) {
        val entity = wordsRepository.findById(word)
            .orElse(Words(word, 0))

        entity.counter++

        wordsRepository.save(entity)
    }
}