package com.refugio.model.adopcion;

import com.refugio.excepciones.PesoInsuficienteException;
import com.refugio.model.mascota.Cocodrilo;
import com.refugio.model.persona.Adoptante;
import com.refugio.model.persona.Empleado;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Cocodrilo")
public class AdopcionCocodrilo extends Adopcion<Cocodrilo> {

    public AdopcionCocodrilo(Cocodrilo mascota, Adoptante adoptante, Empleado empleado) {
        super(empleado, adoptante, mascota);
    }

    public AdopcionCocodrilo() {
        super();
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