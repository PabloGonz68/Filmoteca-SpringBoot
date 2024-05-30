package com.pgs.springboot.filmotecaspringboot.services;

import com.pgs.springboot.filmotecaspringboot.entities.Director;

import java.util.List;
import java.util.Optional;

public interface DirectorService {

    //busqueda
    Optional<Director> buscarPorId(Long id);
    Optional<Director> buscarPorNombre(String nombre);
    List<Director> listarTodosLosDirectores();
    List<Director> buscarPorIds(List<Long> ids);


    //funciones crud
    Director saveDirector(Director director);
    Director actualizarDirector(Director director);
    void  eliminarDirector(Long id) throws ClassNotFoundException;


}
