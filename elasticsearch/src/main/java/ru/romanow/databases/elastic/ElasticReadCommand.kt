package ru.romanow.databases.elastic

import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.shell.standard.ShellCommandGroup
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption
import org.springframework.stereotype.Service
import ru.romanow.databases.common.commands.BooksValueProvider
import ru.romanow.databases.common.events.ProgressBarProgressEvent
import ru.romanow.databases.common.events.ProgressBarResetEvent
import ru.romanow.databases.common.services.BookReader
import ru.romanow.databases.elastic.entity.Sentences
import ru.romanow.databases.elastic.repository.SentenceRepository

@ShellComponent
@ShellCommandGroup("Read Commands")
class ElasticReadCommand(
    private val bookReader: BookReader,
    private val sentenceRepository: SentenceRepository,
    private val publisher: ApplicationEventPublisher,
) {
    private var logger = LoggerFactory.getLogger(ElasticReadCommand::class.java)

    @ShellMethod(value = "read sentences", key = ["read-sentence"])
    fun readSentences(@ShellOption(valueProvider = BooksValueProvider::class, arity = 10) book: String) {
        val sentences = bookReader.readSentencesFromBook(book)

        var index = 1
        val size = sentences.size
        sentences.forEach {
            sentenceRepository.save(Sentences(sentence = it.sentence))
            if (index++ % (size / 100) == 0) {
                val percent = (100 * (index.toDouble() / size)).toInt()
                publisher.publishEvent(ProgressBarProgressEvent(this, percent, "processing $index from $size"))
            }
        }

        publisher.publishEvent(ProgressBarProgressEvent(this, 100, "success"))
        publisher.publishEvent(ProgressBarResetEvent(this))

        logger.info("$book processed")
    }
}