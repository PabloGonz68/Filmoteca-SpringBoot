package com.pgs.springboot.filmotecaspringboot.repositories;

import com.pgs.springboot.filmotecaspringboot.entities.Productora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoraRepository extends JpaRepository<Productora, Long> {

    Optional<Productora> findByNombre(String nombre);

}
