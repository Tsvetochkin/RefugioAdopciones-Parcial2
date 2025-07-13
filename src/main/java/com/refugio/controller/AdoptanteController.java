package com.refugio.controller;

import com.refugio.dao.AdoptanteDAO;
import com.refugio.model.persona.Adoptante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/adoptantes")
public class AdoptanteController {

    private final AdoptanteDAO adoptanteDAO;

    @Autowired
    public AdoptanteController(AdoptanteDAO adoptanteDAO) {
        this.adoptanteDAO = adoptanteDAO;
    }

    @GetMapping
    public List<Adoptante> obtenerTodos() {
        return adoptanteDAO.findAll();
    }
}