package com.example.knowuproject.controle;

import com.example.knowuproject.modelo.Evento;
import com.example.knowuproject.modelo.Usuario;
import com.example.knowuproject.repositorio.EventoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class EventoControllerTest {

    @Autowired
    EventoController eventController;

    @MockBean
    EventoRepository eventRepository;

    @Test
    @DisplayName("Verifica se não há eventos")
    void getSemEventos_status204(){
        when(eventRepository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity response = eventController.buscarEvento();

        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());
        assertFalse(response.hasBody());
    }

    @Test
    @DisplayName("Verifica se há eventos")
    void getEventos_status200(){
        List<Evento> eventsMock = List.of(mock(Evento.class), mock(Evento.class));
        when(eventRepository.findAll()).thenReturn(eventsMock);

        ResponseEntity response = eventController.buscarEvento();

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.hasBody());
        assertEquals(eventsMock, response.getBody());
    }
}