package com.example.knowuproject.requisicao;

import com.example.knowuproject.modelo.Usuario;

public class UsuarioAtualizarDadosRequest extends Usuario {

    private Integer idUsuario;
    private String dataNascimento;
    private String genero;
    private String email;
    private String senha;
    private String nome;
    private String usuario;
    private String descricao;

    public UsuarioAtualizarDadosRequest(Integer idUsuario, String dataNascimento, String genero, String email, String senha, String nome, String usuario, String descricao) {
        this.idUsuario = idUsuario;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.usuario = usuario;
        this.descricao = descricao;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
