package com.refugio.dao;

import com.refugio.model.persona.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoDAO extends JpaRepository<Empleado, Long> {
}