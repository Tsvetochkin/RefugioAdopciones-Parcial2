package com.refugio.mascota;

import com.refugio.adopcion.Adopcion;
import com.refugio.adopcion.AdopcionGato;
import com.refugio.mascota.estado.EstadoMascota;
import com.refugio.persona.Adoptante;
import com.refugio.persona.Empleado;

public class Gato extends Mascota {
    public Gato(String nombre, String fechaNacimiento, double peso, EstadoMascota estado) {
        super(nombre, fechaNacimiento, peso, estado);
    }

    @Override
    public String getTipo() {
        return "Gato";
    }

    @Override
    public Adopcion<?> crearAdopcion(Empleado empleado, Adoptante adoptante) {
        return new AdopcionGato(this, adoptante, empleado);
    }
}