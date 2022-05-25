package com.example.knowuproject.repositorio;

import com.example.knowuproject.modelo.Postagem;
import com.example.knowuproject.modelo.PostagemCurtida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostagemCurtidaRepository extends JpaRepository<PostagemCurtida, Integer> {

    @Query("SELECT p FROM PostagemCurtida p WHERE p.usuario.idUsuario = ?1")
    List<Postagem> findAllByUsuario(Integer id);

    @Query("SELECT COUNT(p) FROM Postagem p WHERE p.usuario.idUsuario = ?1")
    Integer countByUsuario(Integer id);

}
