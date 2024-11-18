package com.alura.literaluraChallenge.repository;

import com.alura.literaluraChallenge.model.LibroClase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILibroRepository extends JpaRepository<LibroClase,Long> {

    @Query("SELECT s.autor FROM LibroClase s")
    List<String> listarAutoresRegistrados();

    @Query("SELECT l.titulo FROM LibroClase l WHERE l.autor = :autor ")
    List<String> librosPorAutor(String autor);

    @Query("SELECT e.nacimiento FROM LibroClase e WHERE e.autor = :autor")
    List<String> nacimientoPorAutor(String autor);

    @Query("SELECT e.muerte FROM LibroClase e WHERE e.autor = :autor")
    List<String> muertePorAutor(String autor);

    @Query("SELECT s FROM LibroClase s WHERE s.idioma = :idioma")
    List<LibroClase> librosPorIdioma(String idioma);
}
