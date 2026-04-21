package com.examen.casino.service;

import com.examen.casino.entity.Jugador;
import com.examen.casino.repository.JugadorRepository;
import org.springframework.stereotype.Service;

@Service
public class JugadorService {

    private final JugadorRepository jugadorRepo;

    public JugadorService(JugadorRepository jugadorRepo) {
        this.jugadorRepo = jugadorRepo;
    }

    public Jugador recargarSaldo(Long jugadorId, double monto) {
        Jugador jugador = jugadorRepo.findById(jugadorId).orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
        jugador.setSaldo(jugador.getSaldo() + monto);
        return jugadorRepo.save(jugador);
    }
}
