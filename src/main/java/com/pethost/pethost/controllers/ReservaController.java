package com.pethost.pethost.controllers;

import com.pethost.pethost.domain.Reserva;
import com.pethost.pethost.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/reservas")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    // Criar nova reserva
    @PostMapping("/criar")
    public ResponseEntity<Reserva> criarReserva(@RequestBody Reserva reserva) {
        try {
            if (reserva.getUid() == null || reserva.getUid().isEmpty()) {
                reserva.setUid(UUID.randomUUID().toString());
            }

            Reserva novaReserva = reservaRepository.save(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaReserva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    // Buscar reserva por UID
    @GetMapping("/{uid}")
    public ResponseEntity<Object> buscarReservaPorUid(@PathVariable String uid) {
        Optional<Reserva> reserva = reservaRepository.findById(uid);

        if (reserva.isPresent()) {
            return ResponseEntity.ok(reserva.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Reserva não encontrada");
        }
    }

    // Listar todas as reservas
    @GetMapping("/todas")
    public ResponseEntity<List<Reserva>> listarReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return ResponseEntity.ok(reservas);
    }

    // Atualizar uma reserva existente
    @PutMapping("/atualizar/{uid}")
    public ResponseEntity<Object> atualizarReserva(@PathVariable String uid, @RequestBody Reserva reservaAtualizada) {
        Optional<Reserva> reservaExistente = reservaRepository.findById(uid);

        if (reservaExistente.isPresent()) {
            Reserva reserva = reservaExistente.get();
            reserva.setUidPet(reservaAtualizada.getUidPet());
            reserva.setDataEntrada(reservaAtualizada.getDataEntrada());
            reserva.setDataSaida(reservaAtualizada.getDataSaida());
            reserva.setTipoReserva(reservaAtualizada.getTipoReserva());
            reserva.setValor(reservaAtualizada.getValor());
            reserva.setStatus(reservaAtualizada.getStatus());
            reserva.setObservacoes(reservaAtualizada.getObservacoes());

            Reserva reservaSalva = reservaRepository.save(reserva);
            return ResponseEntity.ok(reservaSalva);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Reserva não encontrada");
        }
    }

    // Deletar uma reserva
    @DeleteMapping("/deletar/{uid}")
    public ResponseEntity<String> deletarReserva(@PathVariable String uid) {
        Optional<Reserva> reserva = reservaRepository.findById(uid);

        if (reserva.isPresent()) {
            reservaRepository.deleteById(uid);
            return ResponseEntity.ok("Reserva deletada com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Reserva não encontrada");
        }
    }
}
