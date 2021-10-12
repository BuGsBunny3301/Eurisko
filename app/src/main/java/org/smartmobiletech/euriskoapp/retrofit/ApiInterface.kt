package org.smartmobiletech.euriskoapp.retrofit

import org.smartmobiletech.euriskoapp.modules.UserData
import org.smartmobiletech.euriskoapp.room.RoomDb
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {


    @GET("/todos")
    fun getData(): Call<List<UserData>>

    companion object {

        private const val url = "https://jsonplaceholder.typicode.com"

        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }

}