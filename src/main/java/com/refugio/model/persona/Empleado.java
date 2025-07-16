package com.refugio.model.persona;
import com.refugio.model.adopcion.Adopcion;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Empleado extends Persona {
    
    private String password;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Adopcion> adopciones;

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

    public List<Adopcion> getAdopciones() {
        return adopciones;
    }

    public void setAdopciones(List<Adopcion> adopciones) {
        this.adopciones = adopciones;
    }
}