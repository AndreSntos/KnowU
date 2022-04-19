package com.example.knowu.services

import com.example.knowu.model.Usuario
import com.example.knowu.request.UsuarioRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UsuarioService {


    @GET("/usuarios/todos")
    fun list(): Call<List<Usuario>>

    @POST("/usuarios/login")
    fun login(@Body usuarioRequest: UsuarioRequest): Call<List<Usuario>>
}