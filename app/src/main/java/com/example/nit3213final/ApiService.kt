package com.example.nit3213final

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("sydney/auth")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("dashboard/{keypass}")
    fun getDashboardItems(
        @Path("keypass") keypass: String,
        @Query("username") username: String,
        @Query("password") password: String
    ): Call<JsonObject>
}
