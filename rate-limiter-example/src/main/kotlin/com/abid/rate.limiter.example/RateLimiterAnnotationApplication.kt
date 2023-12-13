package com.abid.ratelimiterannotation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RateLimiterAnnotationApplication

fun main(args: Array<String>) {
    runApplication<RateLimiterAnnotationApplication>(*args)
}
