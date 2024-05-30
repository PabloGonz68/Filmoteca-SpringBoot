package com.pgs.springboot.filmotecaspringboot.services;

import com.pgs.springboot.filmotecaspringboot.entities.Categoria;
import com.pgs.springboot.filmotecaspringboot.entities.Pelicula;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    //busqueda
    Optional<Categoria> buscarPorId(Long id);
    Optional<Categoria> buscarPorNombre(String nombre);
    List<Categoria> listarTodasLasCategorias();


    //funciones crud
    Categoria saveCategoria(Categoria categoria);
    Categoria actualizarCategoria(Categoria categoria);
    void  eliminarCategoria(Long id);


}
