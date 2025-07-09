package com.refugio.adopcion;

import com.refugio.excepciones.PesoInsuficienteException;
import com.refugio.mascota.Cocodrilo;
import com.refugio.persona.Adoptante;
import com.refugio.persona.Empleado;

public class AdopcionCocodrilo extends Adopcion<Cocodrilo> {

    public AdopcionCocodrilo(Cocodrilo mascota, Adoptante adoptante, Empleado empleado) {
        super(empleado, adoptante, mascota);
    }

    @Override
    protected void realizarPasoEspecifico() {
        System.out.println("Verificando condiciones del hábitat para el cocodrilo...");
        if (mascota.getPeso() < 150) {
            throw new PesoInsuficienteException(mascota.getNombre(), mascota.getPeso());
        }
        System.out.println("Peso del cocodrilo en nivel aceptable.");
        System.out.println("Recomendando hábitat semiacuático con temperaturas tropicales.");
    }
}