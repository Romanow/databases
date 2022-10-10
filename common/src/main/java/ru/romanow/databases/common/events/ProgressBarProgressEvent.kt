package ru.romanow.databases.common.events

import org.springframework.context.ApplicationEvent

data class ProgressBarProgressEvent(
    var eventSource: Any,
    var progress: Int,
    var message: String,
) : ApplicationEvent(eventSource)