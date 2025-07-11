package com.refugio.adopcion;

import com.refugio.excepciones.PesoExcesivoException;
import com.refugio.mascota.Kanguro;
import com.refugio.persona.Adoptante;
import com.refugio.persona.Empleado;

public class AdopcionKanguro extends Adopcion<Kanguro> {

    public AdopcionKanguro(Kanguro mascota, Adoptante adoptante, Empleado empleado) {
        super(empleado, adoptante, mascota);
    }

    @Override
    protected void realizarPasoEspecifico() {
        System.out.println("Verificando si el kanguro tiene espacio suficiente para saltar...");
        if (mascota.getPeso() > 120) {
            throw new PesoExcesivoException(mascota.getNombre(), mascota.getPeso());
        }
        System.out.println("Peso en rango adecuado para saltar.");
        System.out.println("Recomendando dieta rica en fibra para el kanguro.");
    }
}