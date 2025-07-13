package com.refugio.servicio;

import com.refugio.model.adopcion.Adopcion;
import com.refugio.model.mascota.Mascota;
import com.refugio.model.mascota.estado.EstadoRequiereCuidados;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioMascotas {

    private List<Adopcion<? extends Mascota>> adopciones;

    public ServicioMascotas(List<Adopcion<? extends Mascota>> adopciones) {
        this.adopciones = adopciones;
    }

    public List<Mascota> conCuidadosEspeciales() {
        return adopciones.stream()
                .map(Adopcion::getMascota)
                .filter(m -> m.getEstado() instanceof EstadoRequiereCuidados)
                .collect(Collectors.toList());
    }

    public List<Mascota> porPesoMayorA(double peso) {
        return adopciones.stream()
                .map(Adopcion::getMascota)
                .filter(m -> m.getPeso() > peso)
                .collect(Collectors.toList());
    }

    public List<Mascota> ordenadasPorNombre() {
        return adopciones.stream()
                .map(Adopcion::getMascota)
                .sorted(Comparator.comparing(Mascota::getNombre))
                .collect(Collectors.toList());
    }

    public String concatenarNombres() {
        return adopciones.stream()
                .map(a -> a.getMascota().getNombre())
                .collect(Collectors.joining(", "));
    }
}