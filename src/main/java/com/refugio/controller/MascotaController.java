package com.refugio.controller;

import com.refugio.dao.MascotaDAO;
import com.refugio.model.mascota.Mascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    private final MascotaDAO mascotaDAO;

    @Autowired
    public MascotaController(MascotaDAO mascotaDAO) {
        this.mascotaDAO = mascotaDAO;
    }

    @GetMapping
    public List<Mascota> obtenerTodas() {
        return mascotaDAO.findAll();
    }
}