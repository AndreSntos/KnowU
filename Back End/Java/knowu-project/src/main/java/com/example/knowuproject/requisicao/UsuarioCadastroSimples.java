package com.example.knowuproject.requisicao;

import javax.validation.constraints.*;

public class UsuarioCadastroSimples {
    @NotEmpty(message = "O nome não po ser vazio. Digite um nome válido. EX: Dylan Colonhesi")
    @NotNull
    @Size(min = 5, max = 50)
    @Pattern(regexp = "^[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+$", message = "O nome não deve conter caracteres especiais. Digite um nome válido. EX: Dylan Colonhesi")
    private String nome;

    @NotEmpty(message = "O usuario não po ser vazio. Digite um usuario válido. EX: Nomedeusuario")
    @NotNull
    @Size(min = 5, max = 15)
    private String usuario;

    @Email(message = "Digite um email válido. EX: seuemail@email.com")
    @NotEmpty(message = "O Email não po ser vazio. Digite um email válido. EX: seuemail@email.com")
    @NotNull
    private String email;

    @NotNull(message = "Digite um CPF válido, sem ponto e traço.")
    @NotBlank(message = "Digite um CPF válido.")
    @Size(min = 11, message = "Digite um CPF válido.")
    private String cpf;

    @NotNull
    @NotBlank
    private String dataNascimento;

    @NotNull(message = "O genero não pode ser em branco.")
    @Size(min = 5, max = 50)
    private String genero;

    @NotNull(message = "A senha não pode ser em branco. EX: Abc@1234")
    @NotBlank(message = "A senha não deve conter espaços em branco. EX: Abc@1234")
    @Size(min = 8, max = 20, message = "A senha deve conter no maximo 8 caracteres. EX: Abc@1234")
    private String senha;

    @NotNull(message = "A senha não pode ser em branco. EX: Abc@1234")
    @NotBlank(message = "A senha não deve conter espaços em branco. EX: Abc@1234")
    @Size(min = 8, max = 20, message = "A senha deve conter no maximo 8 caracteres. EX: Abc@1234")
    private String confirmarSenha;

    public UsuarioCadastroSimples(String nome, String usuario, String email, String cpf, String dataNascimento, String genero, String senha, String confirmarSenha) {
        this.nome = nome;
        this.usuario = usuario;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.senha = senha;
        this.confirmarSenha = confirmarSenha;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
