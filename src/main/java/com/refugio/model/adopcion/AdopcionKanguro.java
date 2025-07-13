package com.refugio.model.adopcion;

import com.refugio.model.excepciones.PesoExcesivoException;
import com.refugio.model.mascota.Kanguro;
import com.refugio.model.persona.Adoptante;
import com.refugio.model.persona.Empleado;

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