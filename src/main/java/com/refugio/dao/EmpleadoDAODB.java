package com.refugio.dao;

import com.refugio.persona.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmpleadoDAODB implements EmpleadoDAO {

    private final DataSource dataSource;

    @Autowired
    public EmpleadoDAODB(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void guardar(Empleado empleado) {
        String sql = "INSERT INTO empleado (nombre, edad, direccion, fechaNacimiento) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, empleado.getNombre());
            stmt.setInt(2, empleado.getEdad());
            stmt.setString(3, empleado.getDireccion());
            stmt.setDate(4, Date.valueOf(empleado.getFechaNacimiento()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Empleado buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM empleado WHERE nombre = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Empleado.getInstancia(
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
    public List<Empleado> obtenerTodos() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleado";
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(Empleado.getInstancia(
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