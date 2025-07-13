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

    @ManyToOne(targetEntity = Mascota.class)
    @JoinColumn(name = "mascota_id")
    protected T mascota;

    protected LocalDate fecha;

    public Adopcion(Empleado empleado, Adoptante adoptante, T mascota) {
        this.empleado = empleado;
        this.adoptante = adoptante;
        this.mascota = mascota;
        this.fecha = LocalDate.now(); // автоматически устанавливаем дату
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

    public Empleado getEmpleado() {
        return empleado;
    }

    public Adoptante getAdoptante() {
        return adoptante;
    }

    public T getMascota() {
        return mascota;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}