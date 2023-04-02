package ru.romanow.databases.common.services

import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.support.ResourcePatternResolver
import org.springframework.stereotype.Component
import ru.romanow.databases.common.models.Sentence
import ru.romanow.databases.common.properties.BooksProperties
import java.io.InputStreamReader
import java.util.regex.Pattern

@Component
class BookReaderImpl(
    private var resourceResolver: ResourcePatternResolver,
    private val booksProperties: BooksProperties,
) : BookReader {
    private val wordsPattern = Pattern.compile("((\\b\\S+\\b)((?<=\\.\\w).)?)")
    private val sentencePattern = Pattern.compile("\\b[^.!?]+[.!?]+")

    override fun listBooks(): List<String> {
        return resourceResolver.getResources("classpath:${booksProperties.location}/*.txt")
            .map { it.filename!! }
    }

    override fun readSentencesFromBook(book: String): List<Sentence> {
        return ClassPathResource("${booksProperties.location}/$book")
            .inputStream
            .use {
                val text = InputStreamReader(it).readText()
                sentencePattern
                    .matcher(text)
                    .results()
                    .map { r -> r.group() }
                    .map { s -> Sentence(s, readWords(s)) }
                    .toList()
            }
    }

    override fun readWords(sentence: String) =
        wordsPattern
            .matcher(sentence)
            .results()
            .map { r -> r.group() }
            .map { w -> w.lowercase() }
            .toList()
}