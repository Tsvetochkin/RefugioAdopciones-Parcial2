package com.refugio.servicio;

import com.refugio.adopcion.Adopcion;
import com.refugio.mascota.Mascota;

import java.util.ArrayList;
import java.util.List;

public class ServicioAdopciones {

    private List<Adopcion<? extends Mascota>> adopciones = new ArrayList<>();

    public void registrar(Adopcion<? extends Mascota> adopcion) {
        adopciones.add(adopcion);
    }

    public List<Adopcion<? extends Mascota>> obtenerTodas() {
        return adopciones;
    }

    public boolean existeAdopcionSinMascota() {
        return adopciones.stream().anyMatch(a -> a.getMascota() == null);
    }
}