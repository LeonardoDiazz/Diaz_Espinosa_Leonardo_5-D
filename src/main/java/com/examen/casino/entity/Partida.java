package com.examen.casino.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha;
    private String estado;
    private double apuesta;

    @ManyToOne
    private Jugador jugador;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public double getApuesta() { return apuesta; }
    public void setApuesta(double apuesta) { this.apuesta = apuesta; }
    public Jugador getJugador() { return jugador; }
    public void setJugador(Jugador jugador) { this.jugador = jugador; }
}
