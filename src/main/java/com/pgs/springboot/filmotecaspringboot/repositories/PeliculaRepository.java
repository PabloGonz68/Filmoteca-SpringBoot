package com.pgs.springboot.filmotecaspringboot.repositories;

import com.pgs.springboot.filmotecaspringboot.entities.Categoria;
import com.pgs.springboot.filmotecaspringboot.entities.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    Optional<Pelicula> findByTitulo(String titulo);

    List<Pelicula> findByCategoria(Categoria categoria);

}
