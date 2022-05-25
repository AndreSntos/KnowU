package com.example.knowuproject.controle;


import com.example.knowuproject.modelo.Postagem;

import com.example.knowuproject.modelo.PostagemCurtida;
import com.example.knowuproject.repositorio.LocalidadeRepository;
import com.example.knowuproject.repositorio.PostagemCurtidaRepository;
import com.example.knowuproject.repositorio.PostagemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/postagens-curtida")
@CrossOrigin("*")
public class PostagemCurtidaController {

    @Autowired
    PostagemCurtidaRepository postagemCurtidaRepository;

    @PostMapping("/adicionar")
    public ResponseEntity adicionarCurtida(@RequestBody PostagemCurtida postagemCurtida) {
        postagemCurtidaRepository.save(postagemCurtida);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity exibirPostagemPorUsuario(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(postagemCurtidaRepository.findAllByUsuario(id));
    }

    @GetMapping("/count/{id}")
    public ResponseEntity numeroDePostagensCurtidas(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(postagemCurtidaRepository.countByUsuario(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarCurtida(@PathVariable Integer id) {
        postagemCurtidaRepository.deleteById(id);
        return ResponseEntity.status(200).build();
    }

}

