package de.lathspell.test.controller

import de.lathspell.test.model.Person
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

/**
 * Simple REST Controller for a database of Person objects.
 *
 * Remember that the Person.id is not the array index!
 */
@RestController
@RequestMapping("/person")
class PersonController {
    val database = mutableListOf(
            Person(3, "Tim"),
            Person(4, "Anna"),
            Person(5, "Bob")
    )

    @PostMapping("/")
    fun create(@RequestBody p: Person) {
        if (database.firstOrNull() { it.id == p.id } != null) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "Person with Id ${p.id} is already existing!")
        }
        database.add(p)
    }

    @GetMapping("/")
    fun retrieveAll(): List<Person> = database

    @GetMapping("/{id}")
    fun retrieveById(@PathVariable("id") id: Long): Person? {
        try {
            return database.first { it.id == id }
        } catch (e: NoSuchElementException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/")
    fun updateById(@RequestBody p: Person) {
        deleteById(p.id)
        database.add(p)
    }

    @DeleteMapping
    fun deleteAll() = database.clear()

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable("id") id: Long) = database.removeAt(database.indexOfFirst { it.id == id })
}

