package com.refugio.dao;

import com.refugio.persona.Adoptante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdoptanteDAOH2 implements AdoptanteDAO {

    private static final String URL = "jdbc:h2:~/refugio_db";
    private static final String USER = "sa";
    private static final String PASS = "";

    public AdoptanteDAOH2() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS adoptantes (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nombre VARCHAR(255), " +
                    "edad INT, " +
                    "direccion VARCHAR(255), " +
                    "fecha_nacimiento VARCHAR(10))");
        } catch (SQLException e) {
            throw new RuntimeException("Error creando tabla adoptantes: " + e.getMessage());
        }
    }

    @Override
    public void guardar(Adoptante adoptante) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "INSERT INTO adoptantes (nombre, edad, direccion, fecha_nacimiento) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, adoptante.getNombre());
                stmt.setInt(2, adoptante.getEdad());
                stmt.setString(3, adoptante.getDireccion());
                stmt.setString(4, adoptante.getFechaNacimiento());
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar adoptante: " + e.getMessage());
        }
    }

    @Override
    public Adoptante buscarPorNombre(String nombre) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT * FROM adoptantes WHERE nombre = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nombre);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return new Adoptante(
                            rs.getString("nombre"),
                            rs.getInt("edad"),
                            rs.getString("direccion"),
                            rs.getString("fecha_nacimiento")
                    );
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar adoptante: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Adoptante> obtenerTodos() {
        List<Adoptante> lista = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM adoptantes")) {
            while (rs.next()) {
                lista.add(new Adoptante(
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("direccion"),
                        rs.getString("fecha_nacimiento")
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener adoptantes: " + e.getMessage());
        }
        return lista;
    }
}