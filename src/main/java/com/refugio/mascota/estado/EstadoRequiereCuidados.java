package com.refugio.mascota.estado;

import java.util.Random;

public class EstadoRequiereCuidados implements EstadoMascota {
    private static final Random random = new Random();

    @Override
    public boolean quiereJugar() {
        return random.nextBoolean();
    }

    @Override
    public String mostrarCuidados() {
        return "Cuidados normales + alimentación mejorada y control de hidratación.";
    }
}