package com.refugio.persona;

// Singleton para representar al único empleado del refugio
public class Empleado extends Persona {
    private static Empleado instancia;

    private Empleado(String nombre, int edad, String direccion, String fechaNacimiento) {
        super(nombre, edad, direccion, fechaNacimiento);
    }

    public static Empleado getInstancia(String nombre, int edad, String direccion, String fechaNacimiento) {
        if (instancia == null) {
            instancia = new Empleado(nombre, edad, direccion, fechaNacimiento);
        }
        return instancia;
    }
}