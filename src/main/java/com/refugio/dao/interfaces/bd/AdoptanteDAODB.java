package com.refugio.dao;

import com.refugio.persona.Adoptante;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdoptanteDAODB implements AdoptanteDAO {

    private final String url = "jdbc:h2:mem:refugio";
    private final String user = "sa";
    private final String password = "";

    public AdoptanteDAODB() {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS adoptante (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nombre VARCHAR(255), edad INT, direccion VARCHAR(255), fechaNacimiento DATE)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void guardar(Adoptante adoptante) {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO adoptante (nombre, edad, direccion, fechaNacimiento) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, adoptante.getNombre());
            stmt.setInt(2, adoptante.getEdad());
            stmt.setString(3, adoptante.getDireccion());
            stmt.setDate(4, Date.valueOf(adoptante.getFechaNacimiento()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Adoptante buscarPorNombre(String nombre) {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM adoptante WHERE nombre = ?")) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Adoptante(
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("direccion"),
                        rs.getDate("fechaNacimiento").toString()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Adoptante> obtenerTodos() {
        List<Adoptante> lista = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM adoptante")) {

            while (rs.next()) {
                lista.add(new Adoptante(
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("direccion"),
                        rs.getDate("fechaNacimiento").toString()
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
}