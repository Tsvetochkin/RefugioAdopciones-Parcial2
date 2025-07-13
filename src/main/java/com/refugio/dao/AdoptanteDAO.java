package com.refugio.dao;

import com.refugio.model.persona.Adoptante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AdoptanteDAO extends JpaRepository<Adoptante, Long> {
    Optional<Adoptante> findByNombre(String nombre);
}