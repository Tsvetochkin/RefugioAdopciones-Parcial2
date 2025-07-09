package com.refugio.mascota;

import com.refugio.adopcion.Adopcion;
import com.refugio.adopcion.AdopcionCocodrilo;
import com.refugio.mascota.estado.EstadoMascota;
import com.refugio.persona.Adoptante;
import com.refugio.persona.Empleado;

public class Cocodrilo extends Mascota {
    public Cocodrilo(String nombre, String fechaNacimiento, double peso, EstadoMascota estado) {
        super(nombre, fechaNacimiento, peso, estado);
    }

    @Override
    public String getTipo() {
        return "Cocodrilo";
    }

    @Override
    public Adopcion<?> crearAdopcion(Empleado empleado, Adoptante adoptante) {
        return new AdopcionCocodrilo(this, adoptante, empleado);
    }
}