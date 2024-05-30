package com.pgs.springboot.filmotecaspringboot.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Productora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "productora", cascade = CascadeType.ALL)
    private List<Pelicula> peliculas = new ArrayList<>();
}
