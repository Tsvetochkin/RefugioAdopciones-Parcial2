package com.refugio.controller;

import com.refugio.adopcion.Adopcion;
import com.refugio.persona.Adoptante;
import com.refugio.util.Contenedor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adoptantes")
public class AdoptanteController {

    private final Contenedor contenedor;

    public AdoptanteController() {
        this.contenedor = Contenedor.getInstancia();
    }

    @GetMapping
    public List<Adoptante> obtenerTodos() {
        return contenedor.getAdopciones()
                .stream()
                .map(Adopcion::getAdoptante)
                .distinct()
                .toList();
    }
}