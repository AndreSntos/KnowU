package com.example.knowuproject.resultados.api.google;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

public class LocalidadeGoogle {
    private List<Result> results;
    private String status;

    public List<Result> getResults() {
        return results;
    }

    public LocalidadeGoogle chamarApiGoogle() {

        RestTemplate restTemplate = new RestTemplate();
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplate = restTemplateBuilder.build();

        LocalidadeGoogle localidadeGoogle = restTemplate.getForObject("https://maps.google.com/maps/api/geocode/json?address=Rua+Haddock+Lobo+595,+Cerqueira+Cesar,SP&components=country:BR&key=AIzaSyBvroKF-1rTRd9K-L9P3ILzSCjcFLU1LkQ", LocalidadeGoogle.class);

        return localidadeGoogle;
    }
}
