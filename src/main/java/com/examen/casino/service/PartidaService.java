package com.examen.casino.service;

import com.examen.casino.entity.Jugador;
import com.examen.casino.entity.Partida;
import com.examen.casino.entity.Tiro;
import com.examen.casino.repository.JugadorRepository;
import com.examen.casino.repository.PartidaRepository;
import com.examen.casino.repository.TiroRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class PartidaService {

    private final JugadorRepository jugadorRepo;
    private final PartidaRepository partidaRepo;
    private final TiroRepository tiroRepo;

    public PartidaService(JugadorRepository jugadorRepo, PartidaRepository partidaRepo, TiroRepository tiroRepo) {
        this.jugadorRepo = jugadorRepo;
        this.partidaRepo = partidaRepo;
        this.tiroRepo = tiroRepo;
    }

    public Partida iniciarPartida(Long jugadorId, double apuesta) {
        Jugador jugador = jugadorRepo.findById(jugadorId).orElseThrow(() -> new RuntimeException("Jugador no encontrado"));

        if (!jugador.isActivo()) throw new RuntimeException("Jugador inactivo");
        if (jugador.getSaldo() < apuesta) throw new RuntimeException("Saldo insuficiente");

        jugador.setSaldo(jugador.getSaldo() - apuesta);
        jugadorRepo.save(jugador);

        Partida partida = new Partida();
        partida.setFecha(LocalDateTime.now());
        partida.setEstado("EN_JUEGO");
        partida.setApuesta(apuesta);
        partida.setJugador(jugador);

        return partidaRepo.save(partida);
    }

    public Tiro realizarTiro(Long partidaId) {
        Partida partida = partidaRepo.findById(partidaId).orElseThrow(() -> new RuntimeException("Partida no encontrada"));

        if (!partida.getEstado().equals("EN_JUEGO")) throw new RuntimeException("La partida no está en juego");

        Random random = new Random();
        int dado1 = random.nextInt(6) + 1;
        int dado2 = random.nextInt(6) + 1;
        int suma = dado1 + dado2;

        Tiro tiro = new Tiro();
        tiro.setValorDado1(dado1);
        tiro.setValorDado2(dado2);
        tiro.setSuma(suma);
        tiro.setPartida(partida);

        if (suma == 7 || suma == 11) {
            tiro.setEsGanador(true);
            partida.setEstado("FINALIZADA");
            Jugador jugador = partida.getJugador();
            jugador.setSaldo(jugador.getSaldo() + partida.getApuesta() * 2);
            jugadorRepo.save(jugador);
            partidaRepo.save(partida);
        } else if (suma == 2 || suma == 3 || suma == 12) {
            tiro.setEsGanador(false);
            partida.setEstado("FINALIZADA");
            partidaRepo.save(partida);
        } else {
            tiro.setEsGanador(false);
        }

        return tiroRepo.save(tiro);
    }

    public Partida finalizarPartidaManual(Long partidaId) {
        Partida partida = partidaRepo.findById(partidaId).orElseThrow(() -> new RuntimeException("Partida no encontrada"));
        partida.setEstado("FINALIZADA");
        return partidaRepo.save(partida);
    }

    public List<Partida> obtenerHistorialPartidas(Long jugadorId) {
        return partidaRepo.findByJugadorId(jugadorId);
    }
}
