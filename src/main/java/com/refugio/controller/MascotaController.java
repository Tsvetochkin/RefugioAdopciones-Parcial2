package com.refugio.controller;

import com.refugio.model.adopcion.Adopcion;
import com.refugio.model.mascota.Mascota;
import com.refugio.util.Contenedor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    private final Contenedor contenedor;

    public MascotaController() {
        this.contenedor = Contenedor.getInstancia();
    }

    @GetMapping
    public List<Mascota> obtenerTodas() {
        return contenedor.getAdopciones()
                .stream()
                .map(Adopcion::getMascota)
                .collect(Collectors.toList());
    }
}