package com.refugio.adopcion;

import com.refugio.excepciones.PesoLivianoException;
import com.refugio.mascota.Gato;
import com.refugio.persona.Adoptante;
import com.refugio.persona.Empleado;

public class AdopcionGato extends Adopcion<Gato> {

    public AdopcionGato(Gato mascota, Adoptante adoptante, Empleado empleado) {
        super(empleado, adoptante, mascota);
    }

    @Override
    protected void realizarPasoEspecifico() {
        System.out.println("Verificando si el gato necesita un arenero adicional...");
        if (mascota.getPeso() < 3) {
            throw new PesoLivianoException(mascota.getNombre(), mascota.getPeso());
        }
        System.out.println("Recomendando ubicación tranquila para el gato.");
    }
}