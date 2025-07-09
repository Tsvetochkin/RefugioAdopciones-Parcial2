package com.refugio.dao;

import com.refugio.persona.Adoptante;
import java.util.List;

public interface AdoptanteDAO {
    void guardar(Adoptante adoptante);
    Adoptante buscarPorNombre(String nombre);
    List<Adoptante> obtenerTodos();
}