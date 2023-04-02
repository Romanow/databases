package ru.romanow.databases.common

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import ru.romanow.databases.common.properties.BooksProperties

@AutoConfiguration
@EnableConfigurationProperties(BooksProperties::class)
@ComponentScan("ru.romanow.databases.common")
class CommonAutoConfiguration