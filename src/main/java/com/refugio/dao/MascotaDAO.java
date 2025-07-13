package com.refugio.dao;

import com.refugio.model.mascota.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MascotaDAO extends JpaRepository<Mascota, Long> {
    Optional<Mascota> findByNombre(String nombre);
}