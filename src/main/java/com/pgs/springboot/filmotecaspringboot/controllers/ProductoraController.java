package com.pgs.springboot.filmotecaspringboot.controllers;

import com.pgs.springboot.filmotecaspringboot.entities.Productora;
import com.pgs.springboot.filmotecaspringboot.services.PeliculaService;
import com.pgs.springboot.filmotecaspringboot.services.ProductoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/productoras")
public class ProductoraController {
    @Autowired
    private ProductoraService productoraService;

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevaProductora(Model model){
        model.addAttribute("productora", new Productora());
        return "productora/formulario-productora";
    }

    @PostMapping("/guardar")
    public  String guardarProductora(@ModelAttribute Productora productora){
        Productora productoraGuardada = productoraService.saveProductora(productora);
        return "redirect:/productoras/listar";
    }

    @GetMapping({"/listar", "/"})
    public String listarProductoras(Model model){
        List<Productora> productoras = productoraService.listarTodasLasProductoras();
        model.addAttribute("productoras", productoras);
        return "productora/listar-productoras";
    }

    @GetMapping("/{id}")
    public  String mostrarProductora(@PathVariable Long id, Model model){
        Optional<Productora> productoraOptional = productoraService.buscarPorId(id);
        if (productoraOptional.isPresent()){
            Productora productora = productoraOptional.get();
            model.addAttribute("productora", productora);
            model.addAttribute("peliculas", productora.getPeliculas());
        }
        return "productora/mostrar-productora";
    }

    @GetMapping("/{id}/actualizar")
    public String mostrarFormularioEditarProductora(@PathVariable Long id, Model model){
        Optional<Productora> productoraOptional = productoraService.buscarPorId(id);
        productoraOptional.ifPresent(value -> model.addAttribute("productora", value));
        return "productora/formulario-productora";
    }

    @PostMapping("/{id}/actualizar")
    public String actualizarProductora(@PathVariable Long id, @ModelAttribute Productora productora){
        Optional<Productora> productoraOptional = productoraService.buscarPorId(id);
        if (productoraOptional.isPresent()){
            Productora productoraActual = productoraOptional.get();
            productoraActual.setNombre(productora.getNombre());
            productoraService.actualizaProductora(productoraActual);
        }
        return "redirect:/productoras/listar";

    }

    @GetMapping("/{id}/eliminar")
    public String eliminarProductora(@PathVariable Long id){
        productoraService.eliminarProductora(id);
        return "redirect:/productoras/listar";
    }

    @GetMapping("/{id}/peliculas")
    public String mostrarPeliculasDeProductora(@PathVariable Long id, Model model){
    Optional<Productora> productoraOptional = productoraService.buscarPorId(id);
    if (productoraOptional.isPresent()){
        Productora productora = productoraOptional.get();
        model.addAttribute("productora", productora);
        model.addAttribute("peliculas", productora.getPeliculas());
    }
    return "productora/mostrar-peliculas-productora";
    }


}
