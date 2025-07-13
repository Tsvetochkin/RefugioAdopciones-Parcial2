package com.refugio;

import com.refugio.adopcion.Adopcion;
import com.refugio.dao.AdoptanteDAO;
import com.refugio.dao.EmpleadoDAO;
import com.refugio.excepciones.MascotaException;
import com.refugio.mascota.*;
import com.refugio.mascota.estado.*;
import com.refugio.persona.Adoptante;
import com.refugio.persona.Empleado;
import com.refugio.ticket.Ticket;
import com.refugio.ui.LoginUI;
import com.refugio.util.Contenedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.logging.Logger;

@SpringBootApplication
public class RefugioApplication implements CommandLineRunner {

	@Autowired
	private EmpleadoDAO empleadoDAO;

	@Autowired
	private AdoptanteDAO adoptanteDAO;

	private final Contenedor contenedor = Contenedor.getInstancia();

	private static final Logger logger = Logger.getLogger(RefugioApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(RefugioApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Empleado empleado = Empleado.getInstancia("Noe", 33, "Casa Rosada", "1989-06-15");
		empleadoDAO.save(empleado);
		logger.info("Empleado guardado en base de datos: " + empleado.getNombre());


		Adoptante adoptante1 = new Adoptante("Adam", 28, "Palermo Alto 55", "1995-01-01");
		Adoptante adoptante2 = new Adoptante("Eva", 31, "Belgrano R", "1992-03-20");

		adoptanteDAO.save(adoptante1);
		adoptanteDAO.save(adoptante2);
		logger.info("Adoptantes guardados en base de datos.");

		List<EstadoMascota> estados = Arrays.asList(
				new EstadoSaludable(),
				new EstadoRequiereCuidados(),
				new EstadoEnObservacion()
		);

		List<Mascota> mascotas = List.of(
				new Kanguro("Saltamontes", "2022-01-01", 200, getRandomEstado(estados)),
				new Cocodrilo("Bombardiro", "2021-03-14", 100, getRandomEstado(estados)),
				new Ornitorrinco("Narizon", "2023-05-01", 8, getRandomEstado(estados)),
				new Gato("Michi", "2020-10-10", 4.4, getRandomEstado(estados))
		);

		for (int i = 0; i < mascotas.size(); i++) {
			Mascota mascota = mascotas.get(i);
			Adoptante adoptante = (i % 2 == 0) ? adoptante1 : adoptante2;

			try {
				Adopcion<?> adopcion = mascota.crearAdopcion(empleado, adoptante);
				adopcion.procesarAdopcion();
				contenedor.agregarAdopcion(adopcion);
				logger.info("Adopción registrada para mascota: " + mascota.getNombre());

				Ticket ticket = new Ticket(adopcion);
				ticket.imprimir();
				logger.info("Ticket generado para adopción: " + mascota.getNombre());

				System.out.println("Quiere jugar: " + (mascota.quiereJugar() ? "Sí" : "No"));
				System.out.println();

			} catch (MascotaException e) {
				System.out.println("Error en adopción: " + e.getMessage());
				logger.warning("Fallo en adopción de mascota: " + mascota.getNombre() + " - " + e.getMessage());
				System.out.println();
			}
		}

		System.out.println("Mascotas que quieren jugar:");
		mascotas.stream()
				.filter(Mascota::quiereJugar)
				.map(Mascota::getNombre)
				.forEach(System.out::println);

		System.out.println("\nResumen de adopciones exitosas:");
		System.out.println("Total de adopciones guardadas: " + contenedor.getAdopciones().size());

		System.out.println("Mascotas adoptadas del tipo Gato:");
		contenedor.filtrarAdopciones(adopcion ->
				adopcion.getMascota().getClass().getSimpleName().equals("Gato")
		).forEach(adopcion ->
				System.out.println("- " + adopcion.getMascota().getNombre())
		);

		if (!java.awt.GraphicsEnvironment.isHeadless()) {
			javax.swing.SwingUtilities.invokeLater(() -> new LoginUI().setVisible(true));
		} else {
			System.out.println("GUI не может быть запущен в headless-режиме.");
		}
	}

	private EstadoMascota getRandomEstado(List<EstadoMascota> estados) {
		return estados.get(new Random().nextInt(estados.size()));
	}
}