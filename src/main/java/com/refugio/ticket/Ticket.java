package com.refugio.ticket;

import com.refugio.adopcion.Adopcion;
import com.refugio.mascota.Mascota;
import com.refugio.persona.Adoptante;
import com.refugio.persona.Empleado;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private Adopcion<? extends Mascota> adopcion;

    public Ticket(Adopcion<? extends Mascota> adopcion) {
        this.adopcion = adopcion;
    }

    public void imprimir() {
        Empleado empleado = adopcion.getEmpleado();
        Adoptante adoptante = adopcion.getAdoptante();
        Mascota mascota = adopcion.getMascota();
        LocalDate fecha = adopcion.getFecha();

        System.out.println("--------------------------------------------------");
        System.out.println("              TICKET DE ADOPCIÓN");
        System.out.println("--------------------------------------------------");
        System.out.println("Fecha de Adopción: " + fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        System.out.println();
        System.out.println("Datos del Adoptante:");
        System.out.println("Nombre: " + adoptante.getNombre());
        System.out.println("Edad: " + adoptante.getEdad());
        System.out.println("Dirección: " + adoptante.getDireccion());
        System.out.println();
        System.out.println("Datos de la Mascota:");
        System.out.println("Nombre: " + mascota.getNombre());
        System.out.println("Fecha de Nacimiento: " + mascota.getFechaNacimiento());
        System.out.println("Peso: " + mascota.getPeso());
        System.out.println("Especie: " + mascota.getTipo());
        System.out.println("Recomendaciones de Cuidado: " + mascota.getRecomendacionesCuidado());
        System.out.println();
        System.out.println("Empleado Encargado:");
        System.out.println("Nombre: " + empleado.getNombre());
        System.out.println("Cargo: Encargado del Refugio");
        System.out.println();
        System.out.println("Gracias por adoptar a una mascota!");
        System.out.println("--------------------------------------------------\n");
    }
}