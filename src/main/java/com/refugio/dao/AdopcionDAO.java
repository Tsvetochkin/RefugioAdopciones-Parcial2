package com.refugio.dao;

import com.refugio.model.adopcion.Adopcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdopcionDAO extends JpaRepository<Adopcion, Long> {
}