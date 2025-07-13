package com.refugio.dao;

import com.refugio.model.persona.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoDAO extends JpaRepository<Empleado, Long> {

    Optional<Empleado> findByNombre(String nombre);

    Optional<Empleado> findByNombreAndPassword(String nombre, String password);
}