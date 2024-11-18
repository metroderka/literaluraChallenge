package com.alura.literaluraChallenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record Libro(
    @JsonAlias("title") String titulo,
    @JsonAlias("languages") List<String> idiomas,
    @JsonAlias("download_count") Long descargas,
    @JsonAlias("authors") List<Autor> autores
){}




