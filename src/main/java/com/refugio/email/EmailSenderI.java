package com.refugio.email;

public interface EmailSenderI {
    void enviar(String origen, String destino, String asunto, String cuerpo);
}