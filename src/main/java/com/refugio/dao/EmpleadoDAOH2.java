package com.refugio.dao;

import com.refugio.persona.Empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAOH2 implements EmpleadoDAO {

    private static final String URL = "jdbc:h2:~/refugio_db";
    private static final String USER = "sa";
    private static final String PASS = "";

    public EmpleadoDAOH2() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS empleados (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nombre VARCHAR(255), " +
                    "edad INT, " +
                    "direccion VARCHAR(255), " +
                    "fecha_nacimiento VARCHAR(10))");
        } catch (SQLException e) {
            throw new RuntimeException("Error creando tabla empleados: " + e.getMessage());
        }
    }

    @Override
    public void guardar(Empleado empleado) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "INSERT INTO empleados (nombre, edad, direccion, fecha_nacimiento) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, empleado.getNombre());
                stmt.setInt(2, empleado.getEdad());
                stmt.setString(3, empleado.getDireccion());
                stmt.setString(4, empleado.getFechaNacimiento());
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar empleado: " + e.getMessage());
        }
    }

    @Override
    public Empleado buscarPorNombre(String nombre) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT * FROM empleados WHERE nombre = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nombre);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return Empleado.getInstancia(
                            rs.getString("nombre"),
                            rs.getInt("edad"),
                            rs.getString("direccion"),
                            rs.getString("fecha_nacimiento")
                    );
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar empleado: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Empleado> obtenerTodos() {
        List<Empleado> lista = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM empleados")) {
            while (rs.next()) {
                lista.add(Empleado.getInstancia(
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("direccion"),
                        rs.getString("fecha_nacimiento")
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener empleados: " + e.getMessage());
        }
        return lista;
    }
}