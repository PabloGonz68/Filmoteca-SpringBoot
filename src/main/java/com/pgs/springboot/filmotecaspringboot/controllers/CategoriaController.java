package com.pgs.springboot.filmotecaspringboot.controllers;

import com.pgs.springboot.filmotecaspringboot.entities.Categoria;
import com.pgs.springboot.filmotecaspringboot.entities.Pelicula;
import com.pgs.springboot.filmotecaspringboot.services.CategoriaService;
import com.pgs.springboot.filmotecaspringboot.services.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping({"/listar", "/"})
    public String listarCategorias(Model model){
        List<Categoria> categorias = categoriaService.listarTodasLasCategorias();
        model.addAttribute("categorias", categorias);
         return  "categoria/listar-categorias";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevaCategoria(Model model){
    Categoria categoria = new Categoria();
    model.addAttribute("categoria", categoria);
        return  "categoria/formulario-categoria";
    }

    @PostMapping("/guardar")
    public String guardarCategoria(@ModelAttribute Categoria categoria){
        Categoria categoriaGuardada = categoriaService.saveCategoria(categoria);
        List<Pelicula> peliculas = peliculaService.buscarPorCategoria(categoriaGuardada);
        categoriaGuardada.setPeliculas(peliculas);
        categoriaService.saveCategoria(categoria);
        return "redirect:/categorias/listar";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarCategoria(@PathVariable Long id, Model model){
        Optional<Categoria> categoria = categoriaService.buscarPorId(id);
        categoria.ifPresent(value -> model.addAttribute("categoria", value));
        return  "categoria/formulario-categoria";
    }

    @PostMapping("/{id}/actualizar")
    public String actualizarCategoria(@PathVariable Long id, @ModelAttribute Categoria categoria){
        Categoria categoriaActual = categoriaService.buscarPorId(id).orElse(null);
    //para que no se pierdan las peliculas al editar la categoria
        if (categoriaActual!=null){
        categoria.setPeliculas(categoriaActual.getPeliculas());
        categoriaService.actualizarCategoria(categoria);
    }
        return "redirect:/categorias/listar";
    }

    @GetMapping("*/{id}/eliminar")
    public String eliminarCategoria(@PathVariable Long id){
        categoriaService.eliminarCategoria(id);
        return "redirect:/categorias/listar";

    }
}
