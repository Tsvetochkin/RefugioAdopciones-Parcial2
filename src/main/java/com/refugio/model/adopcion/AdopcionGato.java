package com.refugio.model.adopcion;

import com.refugio.model.excepciones.PesoLivianoException;
import com.refugio.model.mascota.Gato;
import com.refugio.model.persona.Adoptante;
import com.refugio.model.persona.Empleado;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Gato")
public class AdopcionGato extends Adopcion<Gato> {

    public AdopcionGato(Gato mascota, Adoptante adoptante, Empleado empleado) {
        super(empleado, adoptante, mascota);
    }

    public AdopcionGato() {
        super();
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