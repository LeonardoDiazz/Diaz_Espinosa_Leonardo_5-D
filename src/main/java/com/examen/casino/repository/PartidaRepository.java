package com.examen.casino.repository;

import com.examen.casino.entity.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
    List<Partida> findByJugadorId(Long jugadorId);
}
