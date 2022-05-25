package com.example.knowuproject.controle;

import com.example.knowuproject.dto.Usuariodto;
import com.example.knowuproject.modelo.FilaObj;
import com.example.knowuproject.modelo.Localidade;
import com.example.knowuproject.modelo.Postagem;
import com.example.knowuproject.modelo.Usuario;
import com.example.knowuproject.repositorio.LocalidadeRepository;
import com.example.knowuproject.repositorio.PostagemRepository;
import com.example.knowuproject.repositorio.UsuarioRepository;
import com.example.knowuproject.requisicao.UsuarioAtualizarDadosRequest;
import com.example.knowuproject.requisicao.UsuarioAvaliacaoResponse;
import com.example.knowuproject.requisicao.UsuarioRecuperarSenhaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {

    @Autowired
    PostagemRepository postagemRepository;

    @Autowired
    LocalidadeRepository localidadeRepository;


    @PostMapping("/adicionar")
    public ResponseEntity adicionarPostagem(@RequestBody Postagem postagem) {
        postagemRepository.save(postagem);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/todos")
    public ResponseEntity exibirPostagens() {
        return ResponseEntity.status(200).body(postagemRepository.findAll());
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity exibirPostagemPorEvento(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(postagemRepository.findAllByEvento(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarPostagem(@PathVariable Integer id) {
        postagemRepository.deleteById(id);
        return ResponseEntity.status(200).build();
    }

}

