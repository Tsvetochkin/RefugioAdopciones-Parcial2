package com.refugio.adopcion;

import com.refugio.mascota.*;
import com.refugio.persona.Adoptante;
import com.refugio.persona.Empleado;

public class AdopcionFactory {

    public static Adopcion<?> crearAdopcion(Mascota mascota, Adoptante adoptante, Empleado empleado) {
        if (mascota instanceof Gato) {
            return new AdopcionGato((Gato) mascota, adoptante, empleado);
        } else if (mascota instanceof Cocodrilo) {
            return new AdopcionCocodrilo((Cocodrilo) mascota, adoptante, empleado);
        } else if (mascota instanceof Kanguro) {
            return new AdopcionKanguro((Kanguro) mascota, adoptante, empleado);
        } else if (mascota instanceof Ornitorrinco) {
            return new AdopcionOrnitorrinco((Ornitorrinco) mascota, adoptante, empleado);
        } else {
            throw new IllegalArgumentException("Tipo de mascota no soportado: " + mascota.getClass().getSimpleName());
        }
    }
}