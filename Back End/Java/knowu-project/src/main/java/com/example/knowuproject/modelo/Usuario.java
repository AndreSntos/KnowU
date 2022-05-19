package com.example.knowuproject.modelo;



import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;


    @NotEmpty(message = "O nome não po ser vazio. Digite um nome válido. EX: Dylan Colonhesi")
    @NotNull
    @Size(min = 5, max = 50)
    @Pattern(regexp = "^[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+$", message = "O nome não deve conter caracteres especiais. Digite um nome válido. EX: Dylan Colonhesi")
    private String nome;

    @NotEmpty(message = "O usuario não po ser vazio. Digite um usuario válido. EX: Nomedeusuario")
    @NotNull
    @Size(min = 5, max = 15)
    private String usuario;

    private String celular;

    @Email(message = "Digite um email válido. EX: seuemail@email.com")
    @NotEmpty(message = "O Email não po ser vazio. Digite um email válido. EX: seuemail@email.com")
    @NotNull
    private String email;

    private String descricao;

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

    private Integer codigoRecuperaSenha;
//    private Boolean isBloqueado = false;
    private Boolean autenticado;
    private String autenticadoEm;

    //    private String localizacao;
    @OneToOne
    private Localidade localidade;

    @JsonIgnore // será igonrado no JSON
    @Column(length = 20_000_000) //20MB
    private byte[] foto;

    private Integer avaliacao = 100;

    public Usuario(Integer idUsuario, String nome, String usuario, String celular, String email, String cpf, String dataNascimento, String genero) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.usuario = usuario;
        this.celular = celular;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
    }

    public Usuario() {

    }


    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String senha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getCodigoRecuperaSenha() {
        return codigoRecuperaSenha;
    }

    public void setCodigoRecuperaSenha(Integer codigoRecuperaSenha) {
        this.codigoRecuperaSenha = codigoRecuperaSenha;
    }

//    public Boolean getBloqueado() {
//        return isBloqueado;
//    }

//    public void setBloqueado(Boolean bloqueado) {
//        isBloqueado = bloqueado;
//    }


    public Localidade getLocalidade() {
        return localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    //    Fim Getters e Setters


    public String getSenha() {
        return senha;
    }

    public Integer getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Integer avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void bloquearUsuario(String usuario, String motivo) {
        if (usuario.equals(getUsuario())) {
            System.out.println("Usuário bloqueado pelo motivo:" + motivo + "");
//            setBloqueado(true);
        }
    }

    public void desbloquearUsuario(String usuario) {
        if (usuario.equals(getUsuario())) {
            System.out.println("Usuário " + usuario + " desbloqueado com sucesso");
//            setBloqueado(true);
        }
    }

    public void avaliarUsuario(String usuario, Integer nota, String comentario) {
        System.out.println("Avaliação realizada");
    }

    public Boolean getAutenticado() {
        return autenticado;
    }

    public String getAutenticadoEm() {

        return autenticadoEm;
    }

    public void setAutenticado(Boolean autenticado) {
        this.autenticado = autenticado;
    }

    public void setAutenticadoEm(String data) {

        this.autenticadoEm = data;
    }


    public void login() {

        this.setAutenticado(true);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.setAutenticadoEm(LocalDateTime.now().format(formato));

        this.localidade = new Localidade().buscarLocalizacao();

//        this.setLocalizacao(localidadeGoogle.getResults().get(0).toString());
    }

    public void logoff() {
        this.setAutenticadoEm(null);
        this.setAutenticado(false);
    }

    @Override
    public String toString() {
        return String.format("Usuário\n" +
                             "ID usuário: %d\n" +
                             "Nome usuário: %s\n" +
                             "Celular: %s\n" +
                             "Email: %s\n" +
                             "Usuário: %s\n" +
                             "Descrição: %s\n" +
                             "CPF: %s\n" +
                             "Data Nascimento: %s\n" +
                             "Gênero: %s\n" +
                             "Senha: %s\n" +
                             "Código recuperação de senha: %d\n" +
                             "Usuário autenticado: %b\n" +
                             "Autenticado em: %s",
                             this.idUsuario,
                             this.nome,
                             this.celular,
                             this.email,
                             this.usuario,
                             this.descricao,
                             this.cpf,
                             this.dataNascimento,
                             this.genero,
                             this.senha,
                             this.codigoRecuperaSenha,
                             this.autenticado,
                             this.autenticadoEm);
    }
}
