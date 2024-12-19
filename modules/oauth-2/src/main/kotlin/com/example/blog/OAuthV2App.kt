package com.example.blog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OAuthV2App

fun main(args: Array<String>) {
    runApplication<OAuthV2App>(*args)
}
