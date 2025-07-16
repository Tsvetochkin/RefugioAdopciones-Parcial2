package com.refugio.model.mascota;

import com.refugio.model.adopcion.Adopcion;
import com.refugio.model.mascota.estado.EstadoMascota;
import com.refugio.model.persona.Adoptante;
import com.refugio.model.persona.Empleado;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_mascota", discriminatorType = DiscriminatorType.STRING)
public abstract class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected String nombre;
    protected String fechaNacimiento;
    protected double peso;

    @Transient
    protected EstadoMascota estado;

    protected final String recomendacionesCuidado;

    @OneToOne(mappedBy = "mascota", cascade = CascadeType.ALL, orphanRemoval = true)
    private Adopcion adopcion;

    public Mascota(String nombre, String fechaNacimiento, double peso, EstadoMascota estado) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.estado = estado;
        this.recomendacionesCuidado = estado.mostrarCuidados(); // фиксируем один раз
    }

    public Mascota() {
        this.recomendacionesCuidado = "";
    }
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public double getPeso() {
        return peso;
    }

    public EstadoMascota getEstado() {
        return estado;
    }

    public void setEstado(EstadoMascota estado) {
        this.estado = estado;
    }

    public boolean quiereJugar() {
        return estado.quiereJugar();
    }

    public String getRecomendacionesCuidado() {
        return recomendacionesCuidado;
    }

    public abstract String getTipo();

    public abstract Adopcion<?> crearAdopcion(Empleado empleado, Adoptante adoptante);

    public Adopcion getAdopcion() {
        return adopcion;
    }

    public void setAdopcion(Adopcion adopcion) {
        this.adopcion = adopcion;
    }

    @Override
    public String toString() {
        return nombre;
    }
}