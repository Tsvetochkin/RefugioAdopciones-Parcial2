package com.refugio.model.persona;
import jakarta.persistence.Entity;

@Entity
public class Empleado extends Persona {
    
    private String password;

    public Empleado() {
        super();
    }

    public Empleado(String nombre, int edad, String direccion, String fechaNacimiento, String password) {
        super(nombre, edad, direccion, fechaNacimiento);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}