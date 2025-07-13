package com.refugio.model.excepciones;

public class PesoInsuficienteException extends MascotaException {
    public PesoInsuficienteException(String nombreMascota, double pesoActual) {
        super(generarMensaje(nombreMascota, pesoActual));
    }

    private static String generarMensaje(String nombreMascota, double pesoActual) {
        return "La mascota '" + nombreMascota + "' tiene un peso demasiado bajo (" + pesoActual + " kg). "
                + "Recomendación extrema: alimentarlo con un turista tonto.";
    }
}