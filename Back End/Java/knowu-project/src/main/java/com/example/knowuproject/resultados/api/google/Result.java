package com.example.knowuproject.resultados.api.google;

public class Result {

    private String formatted_address;
    public Geometry geometry;

    public String getFormatted_address() {
        return formatted_address;
    }

    @Override
    public String toString() {
        return formatted_address;
    }
}
