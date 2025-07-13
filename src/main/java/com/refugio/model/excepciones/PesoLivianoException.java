package com.refugio.model.excepciones;

public class PesoLivianoException extends MascotaException {
    public PesoLivianoException(String nombre, double peso) {
        super("El gato '" + nombre + "' tiene un peso muy liviano (" + peso + " kg). Se recomienda atención especial y un espacio seguro.");
    }
}