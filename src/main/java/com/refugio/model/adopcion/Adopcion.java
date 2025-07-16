package com.refugio.model.adopcion;

import com.refugio.excepciones.MascotaException;
import com.refugio.model.mascota.Mascota;
import com.refugio.model.persona.Adoptante;
import com.refugio.model.persona.Empleado;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_adopcion", discriminatorType = DiscriminatorType.STRING)
public abstract class Adopcion<T extends Mascota> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    protected Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "adoptante_id")
    protected Adoptante adoptante;

    @OneToOne(targetEntity = Mascota.class)
    @JoinColumn(name = "mascota_id")
    protected T mascota;

    protected LocalDate fechaAdopcion;

    public Adopcion(Empleado empleado, Adoptante adoptante, T mascota) {
        this.empleado = empleado;
        this.adoptante = adoptante;
        this.mascota = mascota;
        this.fechaAdopcion = LocalDate.now(); // автоматически устанавливаем дату
    }

    public Adopcion() {

    }

    public void procesarAdopcion() {
        registrarAdoptante();
        registrarEmpleado();
        registrarMascota();

        try {
            realizarPasoEspecifico();
        } catch (MascotaException e) {
            System.out.println("Error en adopción: " + e.getMessage());
        }
    }

    private void registrarAdoptante() {
        System.out.println("Registrando datos del adoptante...");
    }

    private void registrarEmpleado() {
        System.out.println("Registrando datos del empleado encargado...");
    }

    private void registrarMascota() {
        System.out.println("Guardando información de la mascota...");
    }

    protected abstract void realizarPasoEspecifico() throws MascotaException;

    public Long getId() {
        return id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Adoptante getAdoptante() {
        return adoptante;
    }

    public void setAdoptante(Adoptante adoptante) {
        this.adoptante = adoptante;
    }

    public T getMascota() {
        return mascota;
    }

    public void setMascota(T mascota) {
        this.mascota = mascota;
    }

    public LocalDate getFechaAdopcion() {
        return fechaAdopcion;
    }

    public void setFechaAdopcion(LocalDate fechaAdopcion) {
        this.fechaAdopcion = fechaAdopcion;
    }
}