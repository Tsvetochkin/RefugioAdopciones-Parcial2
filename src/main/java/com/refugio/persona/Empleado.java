package com.refugio.persona;
import jakarta.persistence.Entity;

// Singleton para representar al único empleado del refugio
@Entity
public class Empleado extends Persona {
    private static Empleado instancia;

    public Empleado() {
        super();
    }

    public Empleado(String nombre, int edad, String direccion, String fechaNacimiento) {
        super(nombre, edad, direccion, fechaNacimiento);
    }

    public static Empleado getInstancia(String nombre, int edad, String direccion, String fechaNacimiento) {
        if (instancia == null) {
            instancia = new Empleado(nombre, edad, direccion, fechaNacimiento);
        }
        return instancia;
    }
}