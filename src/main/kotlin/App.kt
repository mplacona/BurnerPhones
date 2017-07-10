package uk.co.placona.BurnerPhones

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class App

fun main(args: Array<String>) {
    System.setProperty("MY_NUMBER", System.getenv("MY_NUMBER"))
    SpringApplication.run(App::class.java, *args)
}