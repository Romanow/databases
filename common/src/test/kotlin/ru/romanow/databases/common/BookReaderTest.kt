package ru.romanow.databases.common

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import ru.romanow.databases.common.properties.BooksProperties
import ru.romanow.databases.common.services.BookReader
import ru.romanow.databases.common.services.BookReaderImpl

internal class BookReaderTest {

    private var bookReader: BookReader

    init {
        val resourcePatternResolver = PathMatchingResourcePatternResolver()
        val booksProperties = BooksProperties("books")
        bookReader = BookReaderImpl(resourcePatternResolver, booksProperties)
    }

    @Test
    fun readSentences() {
        val sentences = bookReader.readSentencesFromBook("test-2.txt")
        assertThat(sentences).hasSize(4)
    }

    @Test
    fun readWords() {
        val words = bookReader
            .readWords("The morning of June 27th was clear and sunny, with the fresh warmth of a full-summer day")

        assertThat(words).hasSize(17)
        assertThat(words).containsExactlyInAnyOrder(
            "the",
            "morning",
            "of",
            "june",
            "27th",
            "was",
            "clear",
            "and",
            "sunny",
            "with",
            "the",
            "fresh",
            "warmth",
            "of",
            "a",
            "full-summer",
            "day"
        )
    }
}
