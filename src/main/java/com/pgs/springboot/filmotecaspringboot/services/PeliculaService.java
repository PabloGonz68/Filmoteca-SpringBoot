package com.pgs.springboot.filmotecaspringboot.services;

import com.pgs.springboot.filmotecaspringboot.entities.Categoria;
import com.pgs.springboot.filmotecaspringboot.entities.Pelicula;

import java.util.List;
import java.util.Optional;

public interface PeliculaService {


    //busqueda
    Optional<Pelicula> buscarPorId(Long id);
    Optional<Pelicula> buscarPorTitulo(String titulo);
    List<Pelicula> listarTodasLasPeliculas();
    List<Pelicula> buscarPorCategoria(Categoria categoria);

        //funciones crud
    Pelicula savePelicula(Pelicula pelicula);
    Pelicula actualizarPelicula(Pelicula pelicula);
    void  eliminarPelicula(Long id);


}
