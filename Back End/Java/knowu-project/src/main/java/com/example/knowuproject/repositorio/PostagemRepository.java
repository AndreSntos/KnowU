package com.example.knowuproject.repositorio;

import com.example.knowuproject.modelo.Postagem;
import com.example.knowuproject.modelo.Usuario;
import com.example.knowuproject.requisicao.UsuarioLoginSimples;
import com.example.knowuproject.requisicao.UsuarioNomeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface PostagemRepository extends JpaRepository<Postagem, Integer> {

    @Query("SELECT p FROM Postagem p WHERE p.evento.idEvento = ?1")
    List<Postagem> findAllByEvento(Integer id);
}
