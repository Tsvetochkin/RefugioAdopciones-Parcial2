package com.refugio.servicio;

import com.refugio.dao.AdopcionDAO;
import com.refugio.model.adopcion.Adopcion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioAdopciones {

    private final AdopcionDAO adopcionDAO;

    public ServicioAdopciones(AdopcionDAO adopcionDAO) {
        this.adopcionDAO = adopcionDAO;
    }

    public void registrar(Adopcion adopcion) {
        adopcionDAO.save(adopcion);
    }

    public List<Adopcion> obtenerTodas() {
        return adopcionDAO.findAll();
    }

    public void actualizar(Adopcion adopcion) {
        adopcionDAO.save(adopcion);
    }

    public void eliminar(Long id) {
        adopcionDAO.deleteById(id);
    }

    public boolean existeAdopcionSinMascota() {
        return adopcionDAO.findAll().stream().anyMatch(a -> a.getMascota() == null);
    }
}