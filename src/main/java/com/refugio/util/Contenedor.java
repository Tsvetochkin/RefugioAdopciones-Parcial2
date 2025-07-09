package com.refugio.util;

import com.refugio.adopcion.Adopcion;
import com.refugio.mascota.Mascota;
import com.refugio.persona.Adoptante;
import com.refugio.persona.Empleado;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Contenedor {

    private static Contenedor instancia;

    private final List<Adopcion<?>> adopciones;
    private final List<Mascota> mascotas;
    private final List<Empleado> empleados;
    private final List<Adoptante> adoptantes;

    private Contenedor() {
        this.adopciones = new ArrayList<>();
        this.mascotas = new ArrayList<>();
        this.empleados = new ArrayList<>();
        this.adoptantes = new ArrayList<>();
    }

    public static synchronized Contenedor getInstancia() {
        if (instancia == null) {
            instancia = new Contenedor();
        }
        return instancia;
    }

    public void agregarAdopcion(Adopcion<?> adopcion) {
        adopciones.add(adopcion);
    }

    public List<Adopcion<?>> getAdopciones() {
        return adopciones;
    }

    public void agregarMascota(Mascota m) {
        mascotas.add(m);
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void agregarEmpleado(Empleado e) {
        empleados.add(e);
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void agregarAdoptante(Adoptante a) {
        adoptantes.add(a);
    }

    public List<Adoptante> getAdoptantes() {
        return adoptantes;
    }

    public void vaciarTodo() {
        adopciones.clear();
        mascotas.clear();
        empleados.clear();
        adoptantes.clear();
    }

    public List<Adopcion<?>> filtrarAdopciones(Predicate<Adopcion<?>> criterio) {
        return adopciones.stream().filter(criterio).collect(Collectors.toList());
    }
}