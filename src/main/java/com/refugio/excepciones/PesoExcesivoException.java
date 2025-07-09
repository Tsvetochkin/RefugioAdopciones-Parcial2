package com.refugio.excepciones;

public class PesoExcesivoException extends MascotaException {
    public PesoExcesivoException(String nombre, double peso) {
        super(generarMensaje(nombre, peso));
    }

    private static String generarMensaje(String nombre, double peso) {
        return "El kanguro '" + nombre + "' tiene un peso excesivo (" + peso + " kg). Recomendación extrema: programar 2–3 rondas de boxeo con Mike Tyson.";
    }
}