package com.examen.casino.controller;

import com.examen.casino.dto.RecargarSaldoDTO;
import com.examen.casino.entity.Jugador;
import com.examen.casino.service.JugadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {

    private final JugadorService jugadorService;

    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @PutMapping("/{jugadorId}/recargar")
    public ResponseEntity<Jugador> recargarSaldo(@PathVariable Long jugadorId, @RequestBody RecargarSaldoDTO dto) {
        Jugador jugador = jugadorService.recargarSaldo(jugadorId, dto.getMonto());
        return ResponseEntity.ok(jugador);
    }
}
