package com.refugio.mascota;

import com.refugio.adopcion.Adopcion;
import com.refugio.adopcion.AdopcionOrnitorrinco;
import com.refugio.mascota.estado.EstadoMascota;
import com.refugio.persona.Adoptante;
import com.refugio.persona.Empleado;

public class Ornitorrinco extends Mascota {
    public Ornitorrinco(String nombre, String fechaNacimiento, double peso, EstadoMascota estado) {
        super(nombre, fechaNacimiento, peso, estado);
    }

    @Override
    public String getTipo() {
        return "Ornitorrinco";
    }

    @Override
    public Adopcion<?> crearAdopcion(Empleado empleado, Adoptante adoptante) {
        return new AdopcionOrnitorrinco(this, adoptante, empleado);
    }
}