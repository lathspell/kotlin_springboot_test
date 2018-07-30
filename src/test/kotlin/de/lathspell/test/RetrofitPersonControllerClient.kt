package de.lathspell.test

import de.lathspell.test.model.Person
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface RetrofitPersonControllerClient {
    @GET("/person/")
    fun listPersons() : Call<List<Person>>

    @GET("/person/{id}")
    fun getPerson(@Path("id") id : Int) : Call<Person>

    @PUT("/person/")
    fun updatePerson(@Body p : Person) : Call<ResponseBody>

    @POST("/person/")
    fun createPerson(@Body p : Person) : Call<ResponseBody>

    @DELETE("/person/{id}")
    fun deletePersonById(@Path("id") id: Int) : Call<ResponseBody>

    @DELETE("/person/")
    fun deleteAllPersons() : Call<ResponseBody>
}