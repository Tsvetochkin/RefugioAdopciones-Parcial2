package com.refugio.servicio;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import com.refugio.dao.AdoptanteDAO;
import com.refugio.dao.EmpleadoDAO;
import com.refugio.dao.MascotaDAO;
import com.refugio.model.mascota.*;
import com.refugio.model.mascota.estado.EstadoEnObservacion;
import com.refugio.model.mascota.estado.EstadoRequiereCuidados;
import com.refugio.model.mascota.estado.EstadoSaludable;
import com.refugio.model.persona.Adoptante;
import com.refugio.model.persona.Empleado;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AdoptanteDAO adoptanteDAO;

    @Autowired
    private EmpleadoDAO empleadoDAO;

    @Autowired
    private MascotaDAO mascotaDAO;

    @Override
    public void run(String... args) throws Exception {
        // Crear y guardar empleados
        Empleado empleado1 = new Empleado("Juan Perez", 30, "Calle Falsa 123", "1994-05-15", "password123");
        Empleado empleado2 = new Empleado("Ana Gomez", 25, "Avenida Siempre Viva 456", "1999-08-20", "securepass");
        empleadoDAO.save(empleado1);
        empleadoDAO.save(empleado2);

        // Crear y guardar adoptantes
        Adoptante adoptante1 = new Adoptante("Maria Rodriguez", 40, "Boulevard de los Sueños Rotos 789", "1984-11-10");
        Adoptante adoptante2 = new Adoptante("Carlos Sanchez", 35, "Plaza de la Soledad 101", "1989-02-25");
        adoptanteDAO.save(adoptante1);
        adoptanteDAO.save(adoptante2);

        // Crear y guardar mascotas
        Mascota mascota1 = new Gato("Milo", "2022-01-20", 4.5, new EstadoSaludable());
        Mascota mascota2 = new Cocodrilo("Cocolito", "2021-07-10", 15.2, new EstadoEnObservacion());
        Mascota mascota3 = new Kanguro("Saltarin", "2020-03-12", 25.0, new EstadoRequiereCuidados());
        Mascota mascota4 = new Ornitorrinco("Perry", "2023-05-01", 1.8, new EstadoSaludable());
        mascotaDAO.save(mascota1);
        mascotaDAO.save(mascota2);
        mascotaDAO.save(mascota3);
        mascotaDAO.save(mascota4);
    }
}