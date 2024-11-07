package com.pethost.pethost.controllers;

import com.pethost.pethost.domain.Reserva;
import com.pethost.pethost.dto.ReservaResponseDto;
import com.pethost.pethost.services.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/reservas")
@Tag(name = "Rotas Reserva")
@CrossOrigin(origins = "*")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping("")
    @Operation(summary = "Listar todas as reservas", description = "Responsável por listar todas as reservas")
    public ResponseEntity<List<Reserva>> listarReservas() {
        return new ResponseEntity<>(reservaService.findAllReservas(), HttpStatus.OK);
    }

    @GetMapping("/{uid}")
    @Operation(summary = "Buscar reserva por UID", description = "Responsável por buscar uma única reserva pelo UID")
    public ResponseEntity<Reserva> listarReservaUnica(@PathVariable(value = "uid") String uid) {
        Reserva reserva = reservaService.buscarPorUid(uid);
        return ResponseEntity.ok(reserva);
    }

    @GetMapping("/user/{uidClient}")
    @Operation(summary = "Listar reservas por UID do usuário", description = "Responsável por listar todas as reservas de um usuário específico pelo UID do cliente")
    public ResponseEntity<List<ReservaResponseDto>> listarReservasPorUserUid(@PathVariable(value = "uidClient") Long uidClient) {
        List<ReservaResponseDto> reservas = reservaService.buscarReservasPorUserUid(uidClient);
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    @PostMapping("/criar")
    @Operation(summary = "Criar uma nova reserva", description = "Responsável por criar uma nova reserva")
    public ResponseEntity<Reserva> criarReserva(@RequestBody Reserva reserva) {
        return new ResponseEntity<>(reservaService.criarReserva(reserva), HttpStatus.CREATED);
    }

    @DeleteMapping("/deletar/{uid}")
    @Operation(summary = "Deletar uma reserva", description = "Responsável por deletar uma reserva pelo UID")
    public ResponseEntity<Void> deletarReserva(@PathVariable(value = "uid") String uid) {
        reservaService.deletarReserva(uid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/atualizar")
    @Operation(summary = "Atualizar uma reserva", description = "Responsável por atualizar uma reserva existente")
    public ResponseEntity<Reserva> atualizarReserva(@RequestBody Reserva reserva) {
        return new ResponseEntity<>(reservaService.atualizarReserva(reserva), HttpStatus.OK);
    }
}
