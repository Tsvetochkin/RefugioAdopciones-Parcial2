package com.refugio.dao;

import com.refugio.persona.Adoptante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AdoptanteDAO extends JpaRepository<Adoptante, Long> {
    Optional<Adoptante> findByNombre(String nombre);
}