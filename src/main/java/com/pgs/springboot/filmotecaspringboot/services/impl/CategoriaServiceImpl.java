package com.pgs.springboot.filmotecaspringboot.services.impl;

import com.pgs.springboot.filmotecaspringboot.entities.Categoria;
import com.pgs.springboot.filmotecaspringboot.repositories.CategoriaRepository;
import com.pgs.springboot.filmotecaspringboot.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Optional<Categoria> buscarPorNombre(String nombre) {
        return categoriaRepository.findByNombre(nombre);
    }

    @Override
    public List<Categoria> listarTodasLasCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria saveCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria actualizarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public void eliminarCategoria(Long id) {
    categoriaRepository.deleteById(id);
    }
}
