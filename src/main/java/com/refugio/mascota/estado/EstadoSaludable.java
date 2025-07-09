package com.refugio.mascota.estado;

public class EstadoSaludable implements EstadoMascota {
    @Override
    public boolean quiereJugar() {
        return true;
    }

    @Override
    public String mostrarCuidados() {
        return "Cuidados normales para una mascota saludable.";
    }
}