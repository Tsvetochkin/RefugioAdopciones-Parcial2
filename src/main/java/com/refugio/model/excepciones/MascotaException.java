package com.refugio.model.excepciones;

public abstract class MascotaException extends RuntimeException {
    public MascotaException(String mensaje) {
        super(mensaje);
    }
}