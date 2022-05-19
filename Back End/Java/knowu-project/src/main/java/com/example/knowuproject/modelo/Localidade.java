package com.example.knowuproject.modelo;

import com.example.knowuproject.resultados.api.google.LocalidadeGoogle;
import com.example.knowuproject.resultados.api.google.Result;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Localidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String endereco;
    private Double latitute;
    private Double longitute;

    public Localidade(String endereco, Double latitute, Double longitute) {
        this.endereco = endereco;
        this.latitute = latitute;
        this.longitute = longitute;
    }

    public Localidade() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Double getLatitute() {
        return latitute;
    }

    public void setLatitute(Double latitute) {
        this.latitute = latitute;
    }

    public Double getLongitute() {
        return longitute;
    }

    public void setLongitute(Double longitute) {
        this.longitute = longitute;
    }

    public Localidade buscarLocalizacao() {

        Localidade localidade = new Localidade();
        LocalidadeGoogle localidadeGoogle = new LocalidadeGoogle();
        localidadeGoogle = localidadeGoogle.chamarApiGoogle();
        List<Result> resultados = localidadeGoogle.getResults();

        for (Result resultado : resultados) {
            localidade.endereco = resultado.getFormatted_address();
            localidade.latitute = resultado.geometry.location.lat;
            localidade.longitute = resultado.geometry.location.lng;
        }
//        localidade.endereco = resultados.getFormatted_address();

        return localidade;
    }
}
