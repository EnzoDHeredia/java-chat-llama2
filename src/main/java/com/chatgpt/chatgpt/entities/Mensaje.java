package com.chatgpt.chatgpt.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Mensaje {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String remitente;
    private boolean esBot;
    private String mensaje;
    private LocalDateTime fechaHora;

    @ManyToOne
    private Conversacion conversacion;

    public Mensaje (){
        this.fechaHora = LocalDateTime.now();
    }
    
    public Mensaje(String remitente, boolean esBot, String mensaje, Conversacion conversacion) {
        this.remitente = remitente;
        this.esBot = esBot;
        this.mensaje = mensaje;
        this.conversacion = conversacion;
        this.fechaHora = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRemitente() {
        return remitente;
    }
    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }
    public boolean isEsBot() {
        return esBot;
    }
    public void setEsBot(boolean esBot) {
        this.esBot = esBot;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }    
}
