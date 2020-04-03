package com.wiremock.example.springbootwiremockexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootWiremockExampleApplication

fun main(args: Array<String>) {
    runApplication<SpringBootWiremockExampleApplication>(*args)
}
