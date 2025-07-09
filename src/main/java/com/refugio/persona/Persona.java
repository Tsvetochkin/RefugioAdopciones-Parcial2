package com.refugio.persona;

public abstract class Persona {
    protected String nombre;
    protected int edad;
    protected String direccion;
    protected String fechaNacimiento;

    public Persona(String nombre, int edad, String direccion, String fechaNacimiento) {
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
}