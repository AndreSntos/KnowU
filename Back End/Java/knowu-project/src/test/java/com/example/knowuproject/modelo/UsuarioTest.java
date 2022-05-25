package com.example.knowuproject.modelo;

import com.example.knowuproject.requisicao.UsuarioLoginSimples;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsuarioTest {

    @Test
    @DisplayName("O construtor deve estar preenchido")
    void construtorUsuarioPreencherCampos() {

        Usuario response = new Usuario(1, "Renato Paulino", "RenOP", "1199999-9999", "renato.paulino@sptech.com.br",
                                       "410.427.638-36", "11/05/1992", "Masculino");

        assertEquals(1, response.getIdUsuario());
        assertEquals("Renato Paulino", response.getNome());
        assertEquals("RenOP", response.getUsuario());
        assertEquals("1199999-9999", response.getCelular());
        assertEquals("renato.paulino@sptech.com.br", response.getEmail());
        assertEquals("410.427.638-36", response.getCpf());
        assertEquals("11/05/1992", response.getDataNascimento());
        assertEquals("Masculino", response.getGenero());
    }
}