package com.refugio.model.persona;
import jakarta.persistence.Entity;

@Entity
public class Adoptante extends Persona {
    public Adoptante() {
        super();
    }
    public Adoptante(String nombre, int edad, String direccion, String fechaNacimiento) {
        super(nombre, edad, direccion, fechaNacimiento);
    }
}