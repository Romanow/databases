package ru.romanow.databases.elastic

import org.springframework.stereotype.Service
import ru.romanow.databases.common.services.OperationProvider
import ru.romanow.databases.elastic.entity.Words
import ru.romanow.databases.elastic.repository.WordsRepository

@Service
class WordsOperationProvider(
    private val wordsRepository: WordsRepository,
) : OperationProvider {

    override fun insert(word: String) {
        val document = wordsRepository.findById(word)
            .orElseGet { Words(word, 0) }

        document.counter++

        wordsRepository.save(document)
    }
}