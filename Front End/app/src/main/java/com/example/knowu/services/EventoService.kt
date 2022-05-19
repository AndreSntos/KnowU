package com.example.knowu.services

import com.example.knowu.model.Evento
import com.example.knowu.model.googleapi.Root
import com.example.knowu.request.EventoAdicionarRequest
import retrofit2.Call
import retrofit2.http.*

interface EventoService {


    @POST("criar-mobile/{id}")
    fun add(
        @Path("id") id: Int, @Body evento: EventoAdicionarRequest
    ): Call<Void>

}