package com.example.knowuproject.requisicao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioLoginSimplesTest {

    @Test
    @DisplayName("O construtor deve estar preenchido")
        // do pacote org.junit.jupiter.api...
    void construtorUsuarioSenhaLogin_devePreencherCampos() {

        UsuarioLoginSimples response = new UsuarioLoginSimples("Kling", "Abcd@1234");

        assertEquals("Kling", response.getUsuario());
        assertEquals("Abcd@1234", response.getSenha());
    }

    @Test
    @DisplayName("O campo nome deve ser igual ao construtor")
    void campoNomeIgualConstrutor(){

        UsuarioLoginSimples response = new UsuarioLoginSimples("Kling", "Abcd@1234");

        assertEquals("Kling", response.getUsuario());
    }

    @Test
    @DisplayName("O campo senha deve ser igual ao construtor")
    void campoSenhaIgualConstrutor(){

        UsuarioLoginSimples response = new UsuarioLoginSimples("Kling", "Abcd@1234");

        assertEquals("Abcd@1234", response.getSenha());
    }

    @Test
    @DisplayName("O nome de usuario não pode ser vazio")
    void campoNomeDeUsuarioNaoPodeSerVazio(){

        UsuarioLoginSimples response = new UsuarioLoginSimples("Kling", "Abcd@1234");

        assertEquals("Kling", response.getUsuario());
    }

    @Test
    @DisplayName("O campo senha nao pode ser vazio")
    void campoSenhaNaoPodeSerVazio(){

        UsuarioLoginSimples response = new UsuarioLoginSimples("Kling", "Abcd@1234");

        assertEquals("Abcd@1234", response.getSenha());
    }
}