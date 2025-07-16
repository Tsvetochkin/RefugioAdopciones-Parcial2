package com.refugio.servicio;

import com.refugio.model.persona.Empleado;

public class SessionManager {

    private static SessionManager instance;
    private Empleado loggedInEmpleado;

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void login(Empleado empleado) {
        this.loggedInEmpleado = empleado;
    }

    public Empleado getLoggedInEmpleado() {
        return loggedInEmpleado;
    }

    public void logout() {
        this.loggedInEmpleado = null;
    }
}