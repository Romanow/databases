package ru.romanow.databases.common

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import ru.romanow.databases.common.properties.BooksProperties

@Configuration
@EnableConfigurationProperties(BooksProperties::class)
@ComponentScan("ru.romanow.databases.common")
class CommonAutoConfiguration