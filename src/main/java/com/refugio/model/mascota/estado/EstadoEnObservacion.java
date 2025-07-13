package com.refugio.model.mascota.estado;

import com.refugio.email.EmailSender;
import com.refugio.email.EmailSenderI;

public class EstadoEnObservacion implements EstadoMascota {
    private EmailSenderI emailSender = new EmailSender();

    @Override
    public boolean quiereJugar() {
        return false;
    }

    @Override
    public String mostrarCuidados() {
        emailSender.enviar("observacion@refugio.com", "veterinario@refugio.com", "Alerta", "La mascota está en observación.");
        return "Todos los cuidados anteriores + alerta al veterinario.";
    }
}