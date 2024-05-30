package com.pgs.springboot.filmotecaspringboot.repositories;

import com.pgs.springboot.filmotecaspringboot.entities.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {


    Optional<Director> findByNombre(String nombre);

}
