package ru.romanow.databases.common.services

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import ru.romanow.databases.common.events.ProgressBarProgressEvent
import ru.romanow.databases.common.events.ProgressBarResetEvent
import ru.romanow.databases.common.utils.ProgressBar

@Service
class ProgressEventListener(
    private val progressBar: ProgressBar,
) {
    @EventListener(ProgressBarProgressEvent::class)
    fun progressEventListener(event: ProgressBarProgressEvent) = progressBar.display(event.progress, event.message)

    @EventListener(ProgressBarResetEvent::class)
    fun resetEventListener(event: ProgressBarResetEvent) = progressBar.reset()
}
