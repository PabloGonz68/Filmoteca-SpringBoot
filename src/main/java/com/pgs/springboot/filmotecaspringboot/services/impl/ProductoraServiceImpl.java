package com.pgs.springboot.filmotecaspringboot.services.impl;

import com.pgs.springboot.filmotecaspringboot.entities.Productora;
import com.pgs.springboot.filmotecaspringboot.repositories.ProductoraRepository;
import com.pgs.springboot.filmotecaspringboot.services.ProductoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductoraServiceImpl implements ProductoraService {
    @Autowired
    ProductoraRepository productoraRepository;
    @Override
    public Optional<Productora> buscarPorId(Long id) {
        return productoraRepository.findById(id);
    }

    @Override
    public Optional<Productora> buscarPorNombre(String nombre) {
        return productoraRepository.findByNombre(nombre);
    }

    @Override
    public List<Productora> listarTodasLasProductoras() {
        return productoraRepository.findAll();
    }

    @Override
    public Productora saveProductora(Productora productora) {
        return productoraRepository.save(productora);
    }

    @Override
    public Productora actualizaProductora(Productora productora) {
        return productoraRepository.save(productora);
    }

    @Override
    public void eliminarProductora(Long id) {
    productoraRepository.deleteById(id);
    }
}
