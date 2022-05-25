package com.example.knowuproject.repositorio;

import com.example.knowuproject.modelo.Localidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface LocalidadeRepository extends JpaRepository<Localidade, Integer> {

    @Query(value = "SELECT b.nome, a.*,(6371 * acos(cos(radians(:latitude)) * cos(radians(latitute)) * cos(radians(:longitude) - radians(longitute)) + sin(radians(:latitude)) * sin(radians(latitute)))) as 'distancia' FROM localidade a JOIN evento b on b.localidade_id = a.id", nativeQuery = true)
    List findByAllEventosProximos(@Param("latitude") Double latitude, @Param("longitude") Double longitude);

    @Query(value = "SELECT b.nome, b.usuario, a.*,(6371 * acos(cos(radians(:latitude)) * cos(radians(latitute)) * cos(radians(:longitude) - radians(longitute)) + sin(radians(:latitude)) * sin(radians(latitute)))) as 'distancia' FROM localidade a JOIN usuario b on b.localidade_id = a.id", nativeQuery = true)
    List findByAllUsuariosProximos(@Param("latitude") Double latitude, @Param("longitude") Double longitude);

}
