package com.example.knowuproject.modelo;


import javax.persistence.*;
import java.util.Date;


@Entity
public class PostagemCurtida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPostagem;
    @ManyToOne
    private Postagem postagem;
    @ManyToOne
    private Usuario usuario;

}
