package com.refugio;

import com.refugio.dao.AdopcionDAO;
import com.refugio.dao.AdoptanteDAO;
import com.refugio.dao.EmpleadoDAO;
import com.refugio.dao.MascotaDAO;
import com.refugio.ui.LoginUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.logging.Logger;

@SpringBootApplication
public class RefugioApplication {

    private static final Logger logger = Logger.getLogger(RefugioApplication.class.getName());

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(RefugioApplication.class)
                .headless(false)
                .run(args);

        AdopcionDAO adopcionDAO = context.getBean(AdopcionDAO.class);
        AdoptanteDAO adoptanteDAO = context.getBean(AdoptanteDAO.class);
        EmpleadoDAO empleadoDAO = context.getBean(EmpleadoDAO.class);
        MascotaDAO mascotaDAO = context.getBean(MascotaDAO.class);

        // Launch the UI
        java.awt.EventQueue.invokeLater(() ->
                new LoginUI(adoptanteDAO, empleadoDAO, mascotaDAO, adopcionDAO).setVisible(true)
        );
    }
}