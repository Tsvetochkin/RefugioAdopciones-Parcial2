package com.refugio.adopcion;

import com.refugio.mascota.Ornitorrinco;
import com.refugio.persona.Adoptante;
import com.refugio.persona.Empleado;

public class AdopcionOrnitorrinco extends Adopcion<Ornitorrinco> {

    public AdopcionOrnitorrinco(Ornitorrinco mascota, Adoptante adoptante, Empleado empleado) {
        super(empleado, adoptante, mascota);
    }

    @Override
    protected void realizarPasoEspecifico() {
        System.out.println("Verificando hábitat semiacuático para el ornitorrinco...");
        if (mascota.getPeso() < 5) {
            System.out.println("Advertencia: El ornitorrinco está por debajo del peso ideal.");
            System.out.println("Recomendando dieta hipercalórica y monitoreo intensivo.");
        } else {
            System.out.println("Recomendando cuidados especiales de temperatura y humedad.");
        }
    }
}