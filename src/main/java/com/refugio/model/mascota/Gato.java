package com.refugio.model.mascota;

import com.refugio.model.adopcion.Adopcion;
import com.refugio.model.adopcion.AdopcionGato;
import com.refugio.model.mascota.estado.EstadoMascota;
import com.refugio.model.persona.Adoptante;
import com.refugio.model.persona.Empleado;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Gato")
public class Gato extends Mascota {
    public Gato(String nombre, String fechaNacimiento, double peso, EstadoMascota estado) {
        super(nombre, fechaNacimiento, peso, estado);
    }

    public Gato() {
        super();
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