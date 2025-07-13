package com.refugio.model.mascota;

import com.refugio.model.adopcion.Adopcion;
import com.refugio.model.adopcion.AdopcionKanguro;
import com.refugio.model.mascota.estado.EstadoMascota;
import com.refugio.model.persona.Adoptante;
import com.refugio.model.persona.Empleado;

public class Kanguro extends Mascota {
    public Kanguro(String nombre, String fechaNacimiento, double peso, EstadoMascota estado) {
        super(nombre, fechaNacimiento, peso, estado);
    }

    @Override
    public String getTipo() {
        return "Kanguro";
    }

    @Override
    public Adopcion<?> crearAdopcion(Empleado empleado, Adoptante adoptante) {
        return new AdopcionKanguro(this, adoptante, empleado);
    }
}