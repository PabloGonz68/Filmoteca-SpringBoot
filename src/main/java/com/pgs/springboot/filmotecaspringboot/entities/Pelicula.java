package com.pgs.springboot.filmotecaspringboot.entities;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
// relaciones de 1 a muchos
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private  Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "productora_id")
    private Productora productora;

    //relacion de muchos a muchos
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "pelicula_director",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id")


    )
    private List<Director> directores = new ArrayList<>();

}
