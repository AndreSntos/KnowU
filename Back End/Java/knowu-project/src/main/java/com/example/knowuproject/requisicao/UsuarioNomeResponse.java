package com.example.knowuproject.requisicao;

public class UsuarioNomeResponse {

    private String nome;
    private String usuario;

    public UsuarioNomeResponse(String nome, String usuario) {
        this.nome = nome;
        this.usuario = usuario;
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
}
