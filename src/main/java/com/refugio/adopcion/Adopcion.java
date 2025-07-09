package com.refugio.adopcion;

import com.refugio.excepciones.MascotaException;
import com.refugio.mascota.Mascota;
import com.refugio.persona.Adoptante;
import com.refugio.persona.Empleado;

import java.time.LocalDate;

public abstract class Adopcion<T extends Mascota> {
    protected Empleado empleado;
    protected Adoptante adoptante;
    protected T mascota;
    protected LocalDate fecha;

    public Adopcion(Empleado empleado, Adoptante adoptante, T mascota) {
        this.empleado = empleado;
        this.adoptante = adoptante;
        this.mascota = mascota;
        this.fecha = LocalDate.now(); // автоматически устанавливаем дату
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