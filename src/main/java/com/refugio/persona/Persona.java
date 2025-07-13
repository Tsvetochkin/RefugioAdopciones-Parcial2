package com.refugio.persona;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@MappedSuperclass
public abstract class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected String nombre;
    protected int edad;
    protected String direccion;
    protected String fechaNacimiento;

    public Persona() {
    }

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