package com.pgs.springboot.filmotecaspringboot.services.impl;

import com.pgs.springboot.filmotecaspringboot.entities.Director;
import com.pgs.springboot.filmotecaspringboot.entities.Pelicula;
import com.pgs.springboot.filmotecaspringboot.repositories.DirectorRepository;
import com.pgs.springboot.filmotecaspringboot.services.DirectorService;
import com.sun.jdi.ClassNotPreparedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorServiceImpl implements DirectorService {
    @Autowired
    private DirectorRepository directorRepository;
    @Override
    public Optional<Director> buscarPorId(Long id) {
        return directorRepository.findById(id);
    }

    @Override
    public Optional<Director> buscarPorNombre(String nombre) {
        return directorRepository.findByNombre(nombre);
    }

    @Override
    public List<Director> listarTodosLosDirectores() {
        return directorRepository.findAll();
    }

    @Override
    public List<Director> buscarPorIds(List<Long> ids) {
        return directorRepository.findAllById(ids);
    }

    @Override
    public Director saveDirector(Director director) {
        return directorRepository.save(director);
    }

    @Override
    public Director actualizarDirector(Director director) {
        return directorRepository.save(director);
    }

    @Override
    public void eliminarDirector(Long id) throws ClassNotFoundException {
    Optional<Director> optionalDirector = directorRepository.findById(id);

    if (optionalDirector.isPresent()){
            Director director = optionalDirector.get();
            eliminarRelacionesDirector(director);
            directorRepository.deleteById(id);
    }else{
        throw new ClassNotFoundException("Error");
    }
    }

    private void eliminarRelacionesDirector(Director director){
        for(Pelicula pelicula:director.getPeliculas()){
            pelicula.getDirectores().remove(director);

        }
        director.getPeliculas().clear();
    }
}
