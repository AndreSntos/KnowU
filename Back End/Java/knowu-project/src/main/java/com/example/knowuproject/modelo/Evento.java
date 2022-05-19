package com.example.knowuproject.modelo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvento;
    private String nome;
    private String descricao;
    private String dataInicio;
    private String dataFim;
    @OneToOne
    private Usuario usuario;
    @OneToOne
    private Localidade localidade;

    /*
    public Evento(Integer idEvento, String nome, String descricao, DateTimeFormat dataInicio, DateTimeFormat dataFim) {
        this.idEvento = idEvento;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
    */

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public Optional getUsuario() {
        Optional<Usuario> usuario;
        return usuario = Optional.ofNullable(this.usuario);
    }

    public void setUsuario(Optional<Usuario> usuario) {
        Usuario usuario1 = usuario.get();
        this.usuario = usuario1;
    }

    public Localidade getLocalidade() {
        return localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }

    public void buscarLocalizacao() {
        this.localidade = new Localidade().buscarLocalizacao();
    }


    //    public void adicionarParticipante(Usuario p){
//        participantes.add(p);
//    }
//    public void exibirParticipantes(){
//        for(Usuario p: participantes){
//            System.out.println(p);
//        }
//    }
//    public void removerParticipante(Integer id){
//        for (Usuario p: participantes){
//            if (p.getIdUsuario().equals(id)){
//                participantes.remove(id);
//            }
//        }
    // }

    @Override
    public String toString() {
        return String.format("Evento\n" +
                             "ID Evento: %d\n" +
                             "Nome Evento: %s\n" +
                             "Descrição do evento: %s\n" +
                             "Data início do evento: %s\n" +
                             "Data fim do evento: %s",
                             this.idEvento,
                             this.nome,
                             this.descricao,
                             this.dataInicio,
                             this.dataFim);
    }
}
