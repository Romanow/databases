package ru.romanow.databases.common.utils

import org.jline.terminal.Terminal
import org.jline.utils.AttributedStringBuilder
import org.jline.utils.AttributedStyle
import org.springframework.stereotype.Component

@Component
class ProgressBar(
    private val terminal: Terminal,
) {
    private var started = false

    fun display(percentage: Int, statusMessage: String?) {
        if (!started) {
            started = true
            terminal.writer().println()
        }

        val x = percentage / 2
        val y = 50 - x
        val message = colorMessage(statusMessage ?: "", YELLOW_COLOR)
        val done: String = colorMessage(DONE_MARKER.repeat(x), GREEN_COLOR)
        val remains = REMAIN_MARKER.repeat(y)

        val progressBar = String.format("%s%s%s%s %d", LEFT_DELIMITER, done, remains, RIGHT_DELIMITER, percentage)

        terminal.writer().println("$CUU\r$DL$progressBar% $message")
        terminal.flush()
    }

    fun reset() {
        started = false
    }

    private fun colorMessage(message: String, color: Int) =
        AttributedStringBuilder()
            .append(message, AttributedStyle.DEFAULT.foreground(color))
            .toAnsi()

    companion object {
        private const val CUU = "\u001B[A"
        private const val DL = "\u001B[1M"
        private const val GREEN_COLOR = 2
        private const val YELLOW_COLOR = 3

        private const val DONE_MARKER = "="
        private const val REMAIN_MARKER = "-"
        private const val LEFT_DELIMITER = "<"
        private const val RIGHT_DELIMITER = ">"
    }
}