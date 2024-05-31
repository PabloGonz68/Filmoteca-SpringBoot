package com.pgs.springboot.filmotecaspringboot.controllers;

import com.pgs.springboot.filmotecaspringboot.entities.Categoria;
import com.pgs.springboot.filmotecaspringboot.entities.Director;
import com.pgs.springboot.filmotecaspringboot.entities.Pelicula;
import com.pgs.springboot.filmotecaspringboot.entities.Productora;
import com.pgs.springboot.filmotecaspringboot.services.CategoriaService;
import com.pgs.springboot.filmotecaspringboot.services.DirectorService;
import com.pgs.springboot.filmotecaspringboot.services.PeliculaService;
import com.pgs.springboot.filmotecaspringboot.services.ProductoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/peliculas")
public class PeliculaController {
    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private ProductoraService productoraService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private DirectorService directorService;

    @GetMapping({"listar", "/"})
    public String listarPeliculas(Model model){
        List<Pelicula> peliculas = peliculaService.listarTodasLasPeliculas();
        model.addAttribute("peliculas", peliculas);
        return "pelicula/listar_peliculas";

    }
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevaPelicula(Model model){
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("productoras", productoraService.listarTodasLasProductoras());
        model.addAttribute("categorias", categoriaService.listarTodasLasCategorias());
        model.addAttribute("directores", directorService.listarTodosLosDirectores());
        return "pelicula/formulario_pelicula";

    }
@PostMapping("/guardar")
    public String guardarPelicula(@ModelAttribute Pelicula pelicula, @RequestParam("productoraId") Long productoraId,
                                  @RequestParam("categoriaId") Long categoriaId, @RequestParam("directoresId")List<Long> directoresId){
    //Obtener y asignar la productora, la categoria y directores a la pelicula

    Optional<Productora> productora = productoraService.buscarPorId(productoraId);
    productora.ifPresent(pelicula::setProductora);

    Optional<Categoria> categoria = categoriaService.buscarPorId(categoriaId);
    categoria.ifPresent(pelicula::setCategoria);

    List<Director> directores = directorService.buscarPorIds(directoresId);
    pelicula.setDirectores(new ArrayList<>(directores));

    peliculaService.savePelicula(pelicula);
    return "redirect:/peliculas/listar";
    }
    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarPelicula(@PathVariable Long id, Model model){
        Optional<Pelicula> pelicula = peliculaService.buscarPorId(id);
       if(pelicula.isPresent()){
           model.addAttribute("pelicula", pelicula.get());
           model.addAttribute("productoras", productoraService.listarTodasLasProductoras());
           model.addAttribute("categorias", categoriaService.listarTodasLasCategorias());
           model.addAttribute("directores", directorService.listarTodosLosDirectores());
       }
        return "pelicula/formulario_pelicula";

    }

    @PostMapping("/{id}/actualizar")
    public String actualizarPelicula(@PathVariable Long id, @ModelAttribute Pelicula pelicula,
                                     @RequestParam("productoraId") Long productoraId,
                                     @RequestParam("categoriaId") Long categoriaId,
                                     @RequestParam("directoresId")List<Long> directoresId){
        //Obtener y asignar la productora, la categoria y directores a la pelicula

        Optional<Productora> productora = productoraService.buscarPorId(productoraId);
        productora.ifPresent(pelicula::setProductora);

        Optional<Categoria> categoria = categoriaService.buscarPorId(categoriaId);
        categoria.ifPresent(pelicula::setCategoria);

        List<Director> directores = directorService.buscarPorIds(directoresId);
        pelicula.setDirectores(new ArrayList<>(directores));

        pelicula.setId(id);
        peliculaService.actualizarPelicula(pelicula);
        return "redirect:/peliculas/listar";
    }

    @GetMapping("/{id}/directores")
    public  String mostrarDirectoresDeLaPelicula(@PathVariable Long id, Model model){
    Optional<Pelicula> peliculaOptional = peliculaService.buscarPorId(id);
    if(peliculaOptional.isPresent()){
        Pelicula pelicula = peliculaOptional.get();
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("directores", pelicula.getDirectores());

    }
    return "libro/mostrar_directores";
    }

    @GetMapping("/{id}/eliminar")
    public String eliminarPelicula(@PathVariable Long id){
        peliculaService.eliminarPelicula(id);
        return "redirect:peliculas/listar";
    }


}
