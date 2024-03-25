package ru.romanow.databases.common.events

import org.springframework.context.ApplicationEvent

data class ProgressBarResetEvent(var eventSource: Any) : ApplicationEvent(eventSource)
