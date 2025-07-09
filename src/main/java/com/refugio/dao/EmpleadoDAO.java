package com.refugio.dao;

import com.refugio.persona.Empleado;
import java.util.List;

public interface EmpleadoDAO {
    void guardar(Empleado empleado);
    Empleado buscarPorNombre(String nombre);
    List<Empleado> obtenerTodos();
}