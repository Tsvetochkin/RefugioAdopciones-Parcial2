package com.refugio.model.adopcion;

import com.refugio.model.mascota.Ornitorrinco;
import com.refugio.model.persona.Adoptante;
import com.refugio.model.persona.Empleado;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Ornitorrinco")
public class AdopcionOrnitorrinco extends Adopcion<Ornitorrinco> {

    public AdopcionOrnitorrinco(Ornitorrinco mascota, Adoptante adoptante, Empleado empleado) {
        super(empleado, adoptante, mascota);
    }

    public AdopcionOrnitorrinco() {
        super();
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