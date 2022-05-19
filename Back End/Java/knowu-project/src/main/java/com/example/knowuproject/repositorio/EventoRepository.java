package com.example.knowuproject.repositorio;

import com.example.knowuproject.modelo.Evento;
import com.example.knowuproject.modelo.ListaObj;
import com.example.knowuproject.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
}
