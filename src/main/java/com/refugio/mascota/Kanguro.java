package com.refugio.mascota;

import com.refugio.adopcion.Adopcion;
import com.refugio.adopcion.AdopcionKanguro;
import com.refugio.mascota.estado.EstadoMascota;
import com.refugio.persona.Adoptante;
import com.refugio.persona.Empleado;

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