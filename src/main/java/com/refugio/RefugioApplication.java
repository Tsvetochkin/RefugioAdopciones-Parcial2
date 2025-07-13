package com.refugio;

import com.refugio.dao.AdopcionDAO;
import com.refugio.dao.AdoptanteDAO;
import com.refugio.dao.EmpleadoDAO;
import com.refugio.dao.MascotaDAO;
import com.refugio.model.persona.Adoptante;
import com.refugio.model.persona.Empleado;
import com.refugio.ui.LoginUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.logging.Logger;

@SpringBootApplication
public class RefugioApplication implements CommandLineRunner {

    private static final Logger logger = Logger.getLogger(RefugioApplication.class.getName());

    @Autowired
    private EmpleadoDAO empleadoDAO;
    @Autowired
    private AdoptanteDAO adoptanteDAO;
    @Autowired
    private MascotaDAO mascotaDAO;
    @Autowired
    private AdopcionDAO adopcionDAO;

    public static void main(String[] args) {
        new SpringApplicationBuilder(RefugioApplication.class)
                .headless(false)
                .run(args);
    }

    @Override
    public void run(String... args) {
        // Initialize with some data
        if (empleadoDAO.count() == 0) {
            Empleado empleado = new Empleado("Noe", 33, "Casa Rosada", "1989-06-15", "password");
            empleadoDAO.save(empleado);
            logger.info("Empleado guardado en base de datos: " + empleado.getNombre());
        }

        if (adoptanteDAO.count() == 0) {
            Adoptante adoptante1 = new Adoptante("Adam", 28, "Palermo Alto 55", "1995-01-01");
            Adoptante adoptante2 = new Adoptante("Eva", 31, "Belgrano R", "1992-03-20");
            adoptanteDAO.save(adoptante1);
            adoptanteDAO.save(adoptante2);
            logger.info("Adoptantes guardados en base de datos.");
        }

        // Launch the UI
        java.awt.EventQueue.invokeLater(() ->
                new LoginUI(adoptanteDAO, empleadoDAO, mascotaDAO, adopcionDAO).setVisible(true)
        );
    }
}