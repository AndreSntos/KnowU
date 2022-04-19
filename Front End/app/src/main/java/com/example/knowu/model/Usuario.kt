package com.example.knowu.model

data class Usuario(val idUsuario: Long , val nome: String, val usuario: String, val celular: String, val email: String, val descricao: String, val cpf: String, val dataNascimento: String, val genero: String, val senha: String, val codigoRecuperaSenha: Integer, val autenticado: Boolean, val autenticadoEm: String)
