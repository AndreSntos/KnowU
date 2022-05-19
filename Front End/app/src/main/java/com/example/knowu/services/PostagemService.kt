package com.example.knowu.services

import com.example.knowu.model.Evento
import com.example.knowu.model.Postagem
import com.example.knowu.model.Usuario
import com.example.knowu.model.googleapi.Root
import com.example.knowu.request.EventoAdicionarRequest
import retrofit2.Call
import retrofit2.http.*

interface PostagemService {


    @GET("todos/{id}")
    fun list(
        @Path("id") id: Int
    ): Call<List<Postagem>>

}