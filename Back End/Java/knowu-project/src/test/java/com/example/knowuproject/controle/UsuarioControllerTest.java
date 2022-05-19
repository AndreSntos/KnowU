package com.example.knowuproject.controle;

import com.example.knowuproject.modelo.Localidade;
import com.example.knowuproject.modelo.Usuario;
import com.example.knowuproject.repositorio.LocalidadeRepository;
import com.example.knowuproject.repositorio.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UsuarioControllerTest {

    @Autowired
    UsuarioController userController;

    @MockBean
    UsuarioRepository userRepository;

    @MockBean
    LocalidadeRepository localidadeRepository;

    @Test
    @DisplayName("Verifica se não há usuários")
    void getSemUsuarios_status204() { //Teste quando não tem usuários
        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity response = userController.exibirUsuarios();

        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());
        assertFalse(response.hasBody());
    }

    @Test
    @DisplayName("Verifica se há usuários")
    void getUsuarios_status200() { //Teste quando tem usuários
        List<Usuario> usersMock = List.of(mock(Usuario.class), mock(Usuario.class));
        when(userRepository.findAll()).thenReturn(usersMock);

        ResponseEntity response = userController.exibirUsuarios();

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.hasBody());
        assertEquals(usersMock, response.getBody());
    }


    @Test
    void patchApenasFoto_status200semCorpo() throws IOException {

        Integer idTeste = 1;
        Usuario usuarioMock = mock(Usuario.class);
        MultipartFile fotoMock = mock(MultipartFile.class);
        Usuario usuario = new Usuario(1, "Renato Paulino", "RenOP", "1199999-9999", "renato.paulino@sptech.com.br",
                "410.427.638-36", "11/05/1992", "Masculino");


        when(userRepository.existsById(idTeste)).thenReturn(true);
        when(userRepository.findById(idTeste)).thenReturn(Optional.of(usuarioMock));
        when(userRepository.save(usuario)).thenReturn(usuarioMock);

        ResponseEntity resposta = userController.patchFoto(idTeste, fotoMock);

        assertEquals(200, resposta.getStatusCodeValue());
        assertFalse(resposta.hasBody());
        assertNull(resposta.getBody());

    }

    @Test
    void patchApenasFoto_status404semCorpo() throws IOException {

        Integer idTeste = 1;
        MultipartFile fotoMock = mock(MultipartFile.class);

        when(userRepository.existsById(idTeste)).thenReturn(false);

        ResponseEntity resposta = userController.patchFoto(idTeste, fotoMock);

        assertEquals(404, resposta.getStatusCodeValue());
        assertFalse(resposta.hasBody());
        assertNull(resposta.getBody());

    }


    @Test
    void getApenasFoto_status200semCorpo() {

        Integer idTeste = 1;
        Usuario usuarioMock = mock(Usuario.class);
        when(userRepository.existsById(idTeste)).thenReturn(true);
        when(userRepository.findById(idTeste)).thenReturn(Optional.of(usuarioMock));

        ResponseEntity resposta = userController.getFoto(idTeste);

        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    void getApenasFoto_status404semCorpo() {

        Integer idTeste = 1;
        when(userRepository.existsById(idTeste)).thenReturn(false);

        ResponseEntity resposta = userController.getFoto(idTeste);

        assertEquals(404, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
        assertFalse(resposta.hasBody());
    }


    @Test
    void postUsuario_status201semCorpo() {

        Usuario usuarioMock = mock(Usuario.class);
        Usuario usuarioTeste = new Usuario(1, "Renato Paulino", "RenOP", "1199999-9999", "renato.paulino@sptech.com.br",
                "410.427.638-36", "11/05/1992", "Masculino");

        when(userRepository.save(usuarioTeste)).thenReturn(usuarioMock);

        ResponseEntity resposta = userController.adicionarUsuario(usuarioTeste);

        assertEquals(201, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
        assertFalse(resposta.hasBody());

    }

    @Test
    void postAutenticarUsuario_status200comCorpo() {

            Usuario usuarioMock = mock(Usuario.class);
            Localidade localidadeMock = mock(Localidade.class);

            Localidade localidade = new Localidade("Bandtec", 333333.5, 44444.6);

            Usuario usuarioTeste = new Usuario(1, "Renato Paulino", "RenOP", "1199999-9999", "renato.paulino@sptech.com.br",
                    "410.427.638-36", "11/05/1992", "Masculino");
            usuarioTeste.setLocalidade(localidade);

            when(userRepository.findByUsuario(usuarioTeste.getUsuario())).thenReturn(usuarioMock);
            when(localidadeRepository.save(usuarioTeste.getLocalidade())).thenReturn(localidadeMock);
            when(userRepository.save(usuarioTeste)).thenReturn(usuarioMock);

            ResponseEntity resposta = userController.autenticarUsuario(usuarioTeste);

            assertEquals(204, resposta.getStatusCodeValue());
            assertFalse(resposta.hasBody());


        }
}