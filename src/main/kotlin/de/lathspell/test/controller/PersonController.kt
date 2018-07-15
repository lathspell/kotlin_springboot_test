package de.lathspell.test.controller

import de.lathspell.test.model.Person
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/person")
class PersonController {
    val database = listOf(
            Person(3, "Tim"),
            Person(4, "Anna"),
            Person(5, "Bob")
    )

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int): Person? {
        return database.firstOrNull() { it.id == id.toLong() }
    }
}