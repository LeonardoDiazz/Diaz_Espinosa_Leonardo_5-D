package com.examen.casino.controller;

import com.examen.casino.dto.IniciarPartidaDTO;
import com.examen.casino.entity.Partida;
import com.examen.casino.entity.Tiro;
import com.examen.casino.service.PartidaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidas")
public class PartidaController {

    private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @PostMapping("/iniciar")
    public ResponseEntity<Partida> iniciarPartida(@RequestBody IniciarPartidaDTO dto) {
        Partida partida = partidaService.iniciarPartida(dto.getJugadorId(), dto.getApuesta());
        return ResponseEntity.ok(partida);
    }

    @PostMapping("/{partidaId}/tiro")
    public ResponseEntity<Tiro> realizarTiro(@PathVariable Long partidaId) {
        Tiro tiro = partidaService.realizarTiro(partidaId);
        return ResponseEntity.ok(tiro);
    }

    @PutMapping("/{partidaId}/finalizar")
    public ResponseEntity<Partida> finalizarPartidaManual(@PathVariable Long partidaId) {
        Partida partida = partidaService.finalizarPartidaManual(partidaId);
        return ResponseEntity.ok(partida);
    }

    @GetMapping("/historial/{jugadorId}")
    public ResponseEntity<List<Partida>> obtenerHistorialPartidas(@PathVariable Long jugadorId) {
        List<Partida> partidas = partidaService.obtenerHistorialPartidas(jugadorId);
        return ResponseEntity.ok(partidas);
    }
}
