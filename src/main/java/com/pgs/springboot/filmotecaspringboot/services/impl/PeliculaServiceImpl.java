package com.pgs.springboot.filmotecaspringboot.services.impl;

import com.pgs.springboot.filmotecaspringboot.entities.Categoria;
import com.pgs.springboot.filmotecaspringboot.entities.Pelicula;
import com.pgs.springboot.filmotecaspringboot.repositories.PeliculaRepository;
import com.pgs.springboot.filmotecaspringboot.services.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServiceImpl implements PeliculaService {
    @Autowired
    PeliculaRepository peliculaRepository;

    @Override
    public Optional<Pelicula> buscarPorId(Long id) {
        return peliculaRepository.findById(id);
    }

    @Override
    public Optional<Pelicula> buscarPorTitulo(String titulo) {
        return peliculaRepository.findByTitulo(titulo);
    }

    @Override
    public List<Pelicula> listarTodasLasPeliculas() {
        return peliculaRepository.findAll();
    }

    @Override
    public List<Pelicula> buscarPorCategoria(Categoria categoria) {
        return peliculaRepository.findByCategoria(categoria);
    }

    @Override
    public Pelicula savePelicula(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    @Override
    public Pelicula actualizarPelicula(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    @Override
    public void eliminarPelicula(Long id) {
    peliculaRepository.deleteById(id);
    }
}
