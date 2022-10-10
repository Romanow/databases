package ru.romanow.databases.common.commands

import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.shell.CompletionContext
import org.springframework.shell.CompletionProposal
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption
import org.springframework.shell.standard.ValueProvider
import org.springframework.stereotype.Component
import ru.romanow.databases.common.events.ProgressBarProgressEvent
import ru.romanow.databases.common.events.ProgressBarResetEvent
import ru.romanow.databases.common.services.BookReader
import ru.romanow.databases.common.services.OperationProvider

@ShellComponent
class ReadCommand(
    private val bookReader: BookReader,
    private val operationProvider: OperationProvider,
    private val publisher: ApplicationEventPublisher,
) {
    private var logger = LoggerFactory.getLogger(ReadCommand::class.java)

    @ShellMethod("list books")
    fun list() = bookReader.listBooks()

    @ShellMethod("read book")
    fun read(@ShellOption(valueProvider = BooksValueProvider::class, arity = 10) book: String) {
        val sentences = bookReader.readSentencesFromBook(book)

        var index = 1
        val size = sentences.flatMap { it.words }.size
        sentences
            .flatMap { it.words }
            .forEach {
                operationProvider.insertWord(it)
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

@Component
class BooksValueProvider(
    private var bookReader: BookReader,
) : ValueProvider {
    override fun complete(completionContext: CompletionContext): List<CompletionProposal> {
        return bookReader.listBooks().map { CompletionProposal(it) }
    }

}