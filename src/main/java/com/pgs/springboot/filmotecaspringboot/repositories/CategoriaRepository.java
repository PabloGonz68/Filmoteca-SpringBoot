package com.pgs.springboot.filmotecaspringboot.repositories;

import com.pgs.springboot.filmotecaspringboot.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
Optional<Categoria> findByNombre(String nombre);
}
