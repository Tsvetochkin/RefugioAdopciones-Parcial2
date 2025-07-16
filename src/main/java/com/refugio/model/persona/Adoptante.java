package com.refugio.model.persona;
import com.refugio.model.adopcion.Adopcion;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Adoptante extends Persona {

    @OneToMany(mappedBy = "adoptante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Adopcion> adopciones;

    public Adoptante() {
        super();
    }
    public Adoptante(String nombre, int edad, String direccion, String fechaNacimiento) {
        super(nombre, edad, direccion, fechaNacimiento);
    }

    public List<Adopcion> getAdopciones() {
        return adopciones;
    }

    public void setAdopciones(List<Adopcion> adopciones) {
        this.adopciones = adopciones;
    }
}