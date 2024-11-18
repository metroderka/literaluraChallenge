package com.alura.literaluraChallenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name="libros")
public class LibroClase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    private String idioma;
    private Long descargas;
    private String autor;
    private String nacimiento;
    private String muerte;
    public LibroClase(){};

    public LibroClase (Libro libro) {
        this.titulo = libro.titulo();
        this.descargas = libro.descargas();

        Optional<String> primerIdioma = libro.idiomas().stream().findFirst();
        if(primerIdioma.isPresent()){
            this.idioma = primerIdioma.get();
        } else { this.idioma = "NA";}

        Optional<Autor> primerAutor = libro.autores().stream().findFirst();
        if(primerAutor.isPresent()){
            this.autor = primerAutor.get().nombre();
            if(primerAutor.get().nacimientoAno()!=null){
                this.nacimiento = primerAutor.get().nacimientoAno().toString();
            } else { this.nacimiento = "-1";}
            if(primerAutor.get().muerteAno()!=null){
                this.muerte = primerAutor.get().muerteAno().toString();
            } else { this.muerte = "-1";}
        } else {
            this.autor = "NA";
            this.muerte = "-1";
            this.nacimiento = "-1";
        }

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Long getDescargas() {
        return descargas;
    }

    public void setDescargas(Long descargas) {
        this.descargas = descargas;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getMuerte() {
        return muerte;
    }

    public void setMuerte(String muerte) {
        this.muerte = muerte;
    }

    @Override
    public String toString() {
        return  "\n****LIBRO****" +
                "\n Título: '" + titulo + '\'' +
                "\n Autor: " + autor +
                "\n Idioma: " + idioma +
                "\n Número de descargas: " + descargas +
                "\n*************\n" ;

    }
}
