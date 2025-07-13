package com.refugio.dao;

import com.refugio.persona.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmpleadoDAO extends JpaRepository<Empleado, Long> {
}