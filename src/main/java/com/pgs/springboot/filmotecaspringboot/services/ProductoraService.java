package com.pgs.springboot.filmotecaspringboot.services;

import com.pgs.springboot.filmotecaspringboot.entities.Productora;

import java.util.List;
import java.util.Optional;

public interface ProductoraService {


    Optional<Productora> buscarPorId(Long id);
    Optional<Productora> buscarPorNombre(String nombre);
    List<Productora> listarTodasLasProductoras();

    Productora saveProductora(Productora productora);
    Productora actualizaProductora (Productora productora);
    void eliminarProductora(Long id);
}
