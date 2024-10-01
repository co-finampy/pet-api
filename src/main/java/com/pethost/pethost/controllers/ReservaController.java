package com.pethost.pethost.controllers;

import com.finampy.pethost.domain.Reserva;
import com.pethost.pethost.services.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/reservas")
@Tag(name = "Rotas Reserva")
@CrossOrigin(origins = "*")
public class ReservaController {

    @Autowired
    ReservaService reservaService;

    @GetMapping("")
    @Operation(summary = "Listar reservas", description = "Responsável por listar todas as reservas")
    public List<Reserva> listarReservas() {
        return reservaService.findAllReservas();
    }

    @GetMapping("{uid}")
    @Operation(summary = "Buscar reserva por UID", description = "Responsável por buscar uma única reserva pelo UID")
    public ResponseEntity<Reserva> listarReservaUnica(@PathVariable(value = "uid") String uid) {
        Reserva reserva = reservaService.buscarPorUid(uid);
        if (reserva != null) {
            return ResponseEntity.ok(reserva);
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found
        }
    }

    @PostMapping("")
    @Operation(summary = "Criar uma nova reserva", description = "Responsável por criar uma nova reserva")
    public Reserva criarReserva(@RequestBody Reserva reserva) {
        return reservaService.criarReserva(reserva);
    }

    @DeleteMapping("/{uid}")
    @Operation(summary = "Deletar reserva", description = "Responsável por deletar uma reserva pelo UID")
    public void deletarReserva(@PathVariable(value = "uid") String uid) {
        reservaService.deletarReserva(uid);
    }

    @PutMapping("")
    @Operation(summary = "Atualizar reserva", description = "Responsável por atualizar uma reserva existente")
    public Reserva atualizarReserva(@RequestBody Reserva reserva) {
        return reservaService.atualizarReserva(reserva);
    }
}
