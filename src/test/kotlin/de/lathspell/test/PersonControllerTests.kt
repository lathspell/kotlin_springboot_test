package de.lathspell.test

import de.lathspell.test.model.Person
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerTests {

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    private val persons = listOf(
            Person(3, "Tim"),
            Person(4, "Anna"),
            Person(5, "Bob")
    )

    @Before
    fun before() {
        restTemplate.delete("/person/")
        persons.forEach() {
            restTemplate.postForEntity<Unit>("/person/", it)
        }
    }

    @Test
    fun getAll() {
        val response = restTemplate.getForEntity<List<Person>>("/person/")
        assertEquals(3, response.body!!.size)
    }

    @Test
    fun getOne() {
        val response = restTemplate.getForEntity<Person>("/person/3")
        assertEquals(Person(3, "Tim"), response.body)
    }

    @Test
    fun getNotFound() {
        assertEquals(HttpStatus.NOT_FOUND, restTemplate.getForEntity<Unit>("/person/666").statusCode)
    }

    @Test
    fun update() {
        restTemplate.put("/person/", Person(3, "Timmi"))
        val response = restTemplate.getForEntity<Person>("/person/3")
        assertEquals(Person(3, "Timmi"), response.body)
    }

    @Test
    fun create() {
        assertEquals(HttpStatus.NOT_FOUND, restTemplate.getForEntity<Unit>("/person/42").statusCode)

        val p = Person(42, "Maxime")
        restTemplate.postForEntity<Person>("/person/", p)

        val response = restTemplate.getForEntity<Person>("/person/42")
        assertEquals(p, response.body)
    }

    @Test
    fun delete() {
        assertEquals(HttpStatus.OK, restTemplate.getForEntity<Person>("/person/3").statusCode)

        restTemplate.delete("/person/3")

        assertEquals(HttpStatus.NOT_FOUND, restTemplate.getForEntity<Unit>("/person/3").statusCode)
    }
}
