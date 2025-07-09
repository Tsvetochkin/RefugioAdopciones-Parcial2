package com.refugio.email;

public class EmailSender implements EmailSenderI {
    @Override
    public void enviar(String origen, String destino, String asunto, String cuerpo) {
        System.out.println("Email enviado de " + origen + " a " + destino + " | Asunto: " + asunto + " | Cuerpo: " + cuerpo);
    }
}