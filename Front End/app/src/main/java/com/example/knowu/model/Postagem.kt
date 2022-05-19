package com.example.knowu.model

import java.util.*

data class Postagem(val postagem: String, val usuario: Usuario, val evento: Evento, val dataPostagem: Date, val imagem: Int, val curtida: Boolean) {

    override fun toString(): String {
        return "Postagem(postagem='$postagem', usuario=$usuario, evento=$evento, dataPostagem=$dataPostagem, imagem=$imagem, curtida=$curtida)"
    }
}