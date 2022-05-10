package com.example.knowu.services

import com.example.knowu.model.LocalizacaoEvento
import com.example.knowu.model.Usuario
import com.example.knowu.request.UsuarioAdicionarRequest
import com.example.knowu.request.UsuarioLoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CEPService {


    @GET("/cep/{cep}.json")
    fun list(@Path("cep") cep: String): Call<LocalizacaoEvento>

}