package com.refugio.servicio;

import com.refugio.adopcion.Adopcion;
import com.refugio.mascota.Mascota;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ServicioEstadisticas {

    private List<Adopcion<? extends Mascota>> adopciones;

    public ServicioEstadisticas(List<Adopcion<? extends Mascota>> adopciones) {
        this.adopciones = adopciones;
    }

    public List<Mascota> obtenerConCuidadosEspeciales() {
        return adopciones.stream()
                .map(Adopcion::getMascota)
                .filter(m -> m.getEstado().getClass().getSimpleName().equals("EstadoRequiereCuidados"))
                .collect(Collectors.toList());
    }

    public List<String> obtenerNombresAdoptantes() {
        return adopciones.stream()
                .map(a -> a.getAdoptante().getNombre())
                .collect(Collectors.toList());
    }

    public Map<String, Long> contarMascotasPorEspecie() {
        return adopciones.stream()
                .map(Adopcion::getMascota)
                .map(Mascota::getTipo)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public List<Mascota> ordenarMascotasPorNombre() {
        return adopciones.stream()
                .map(Adopcion::getMascota)
                .sorted(Comparator.comparing(Mascota::getNombre))
                .collect(Collectors.toList());
    }

    public List<Mascota> filtrarPorPeso(double minimo) {
        return adopciones.stream()
                .map(Adopcion::getMascota)
                .filter(m -> m.getPeso() > minimo)
                .collect(Collectors.toList());
    }

    public String concatenarNombresMascotas() {
        return adopciones.stream()
                .map(Adopcion::getMascota)
                .map(Mascota::getNombre)
                .collect(Collectors.joining(", "));
    }

    public double promedioEdadAdoptantes() {
        return adopciones.stream()
                .mapToInt(a -> a.getAdoptante().getEdad())
                .average()
                .orElse(0);
    }

    public List<Adopcion<? extends Mascota>> filtrarAdopcionesRecientes() {
        LocalDate hoy = LocalDate.now();
        return adopciones.stream()
                .filter(a -> a.getFecha().isAfter(hoy.minusDays(30)))
                .collect(Collectors.toList());
    }

    public List<String> transformarMascotas() {
        return adopciones.stream()
                .map(Adopcion::getMascota)
                .map(m -> "Nombre: " + m.getNombre() + ", Especie: " + m.getTipo())
                .collect(Collectors.toList());
    }

    public boolean hayAdopcionesSinMascota() {
        return adopciones.stream()
                .anyMatch(a -> a.getMascota() == null);
    }
}