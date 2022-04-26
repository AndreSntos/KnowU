package com.example.knowu.services

import com.example.knowu.model.Usuario
import com.example.knowu.request.UsuarioAdicionarRequest
import com.example.knowu.request.UsuarioLoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UsuarioService {


    @GET("/usuarios/todos")
    fun list(): Call<List<Usuario>>

    @POST("/usuarios/login")
    fun login(@Body usuarioLoginRequest: UsuarioLoginRequest): Call<Int>

    @POST("/usuarios/adicionar")
    fun adicionar(@Body usuarioAdicionarRequest: UsuarioAdicionarRequest): Call<Void>
}