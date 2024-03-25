package ru.romanow.databases.common.services

import ru.romanow.databases.common.models.Sentence

interface BookReader {
    fun listBooks(): List<String>
    fun readSentencesFromBook(book: String): List<Sentence>
    fun readWords(sentence: String): List<String>
}
