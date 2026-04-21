package com.examen.casino.dto;

public class IniciarPartidaDTO {

    private Long jugadorId;
    private double apuesta;

    public IniciarPartidaDTO() {}

    public Long getJugadorId() { return jugadorId; }
    public void setJugadorId(Long jugadorId) { this.jugadorId = jugadorId; }

    public double getApuesta() { return apuesta; }
    public void setApuesta(double apuesta) { this.apuesta = apuesta; }
}
