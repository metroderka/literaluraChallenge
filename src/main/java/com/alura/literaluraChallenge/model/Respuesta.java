package com.alura.literaluraChallenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Respuesta(
        @JsonAlias("count") Integer numeroHits,
        @JsonAlias("results") List<Libro> listaLibros
) {
}
