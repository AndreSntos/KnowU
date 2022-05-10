package com.example.knowu.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rest {


    fun getInstance(url: String): Retrofit {
        return Retrofit.Builder().baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}