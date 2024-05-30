package com.pgs.springboot.filmotecaspringboot.controllers;

import com.pgs.springboot.filmotecaspringboot.entities.Director;
import com.pgs.springboot.filmotecaspringboot.services.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/directores")
public class DirectorController {
    @Autowired
    DirectorService directorService;

    @GetMapping({"listar", "/"})
    public String listarDirectores(Model model){
        List<Director> directores = directorService.listarTodosLosDirectores();
    model.addAttribute("directores", directores);
    return "director/lista:directores";
    }
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoDirector(Model model){
        model.addAttribute("director", new Director());
        return "director/formulario_director";
    }

    @PostMapping("/guardar")
    public String guardarDirector(@ModelAttribute Director director){
        directorService.saveDirector(director);
        return "redirect:/directores/listar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarDirector(@PathVariable Long id, Model model){
        Optional<Director> director = directorService.buscarPorId(id);
        director.ifPresent(value -> model.addAttribute("director", value));
        return "director/formulario_director";
    }

    @GetMapping
    public String eliminarDirector(@PathVariable Long id) throws ClassNotFoundException {
        directorService.eliminarDirector(id);
        return "redirect:/directores/listar";
    }



}
