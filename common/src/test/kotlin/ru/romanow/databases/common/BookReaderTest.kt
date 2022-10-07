package ru.romanow.databases.common

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import ru.romanow.databases.common.services.BookReader

@ActiveProfiles("test")
@SpringBootTest(classes = [CommonAutoConfiguration::class])
internal class BookReaderTest {

    @Autowired
    private lateinit var bookReader: BookReader

    @Test
    fun readSentences() {
        val sentences = bookReader.readSentences()
        assertThat(sentences).hasSize(6)
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