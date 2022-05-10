package com.example.knowu.services

import com.example.knowu.model.googleapi.Root
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MetricasService {


    @GET("json")
    fun list(
        @Query("address") endereco: String,
        @Query("country") pais: String,
        @Query("key") chave: String,
    ): Call<Root>

}