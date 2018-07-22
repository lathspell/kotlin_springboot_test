package de.lathspell.test

import de.lathspell.test.model.Person
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RetrofitPersonControllerTests {

    @LocalServerPort
    private var port: Int = 0

    private lateinit var client: RetrofitPersonControllerClient

    private val persons = listOf(
            Person(3, "Tim"),
            Person(4, "Anna"),
            Person(5, "Bob")
    )

    @Before
    fun before() {
        val retrofit = Retrofit.Builder().baseUrl("http://localhost:$port/").addConverterFactory(JacksonConverterFactory.create()).build()
        client = retrofit.create(RetrofitPersonControllerClient::class.java)

        client.deleteAllPersons().execute()
        persons.forEach() {
            client.createPerson(it).execute()
        }
    }

    @Test
    fun getAll() {
        val response = client.listPersons().execute()
        assertEquals(3, response.body()!!.size)
    }

    @Test
    fun getOne() {
        val response = client.getPerson(3).execute()
        assertEquals(Person(3, "Tim"), response.body())
    }

    @Test
    fun getNotFound() {
        assertEquals(404, client.getPerson(666).execute().code())
    }

    @Test
    fun update() {
        client.updatePerson(Person(3, "Timmi")).execute()
        val response = client.getPerson(3).execute()
        assertEquals(Person(3, "Timmi"), response.body())
    }

    @Test
    fun create() {
        assertEquals(404, client.getPerson(42).execute().code())

        val p = Person(42, "Maxime")
        client.createPerson(p).execute()

        val response = client.getPerson(42).execute()
        assertEquals(p, response.body())
    }

    @Test
    fun delete() {
        assertEquals(200, client.getPerson(3).execute().code())

        client.deletePersonById(3).execute()

        assertEquals(404, client.getPerson(3).execute().code())
    }
}
