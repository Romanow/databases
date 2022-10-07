package ru.romanow.databases.common.services

import org.springframework.core.io.support.ResourcePatternResolver
import org.springframework.stereotype.Component
import ru.romanow.databases.common.models.Sentence
import ru.romanow.databases.common.properties.BooksProperties
import java.nio.file.Files
import java.util.regex.Pattern
import kotlin.streams.toList

@Component
class BookReader(
    private var resourceResolver: ResourcePatternResolver,
    private val booksProperties: BooksProperties,
) {
    private val wordsPattern = Pattern.compile("((\\b[^\\s]+\\b)((?<=\\.\\w).)?)")
    private val sentencePattern = Pattern.compile("\\b[^.!?]+[.!?]+")

    fun readSentences(): List<Sentence> {
        val files = resourceResolver.getResources("classpath:${booksProperties.location}/*.txt")
        return files.flatMap {
            val text = Files.readString(it.file.toPath())
            sentencePattern
                .matcher(text)
                .results()
                .map { r -> r.group() }
                .map { s -> Sentence(s, readWords(s)) }
                .toList()
        }
    }

    fun readWords(sentence: String) =
        wordsPattern
            .matcher(sentence)
            .results()
            .map { r -> r.group() }
            .map { w -> w.lowercase() }
            .toList()
}