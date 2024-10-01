package com.pethost.pethost.controllers;

import com.pethost.pethost.domain.Calendario; // Ajuste o pacote de importação
import com.pethost.pethost.services.CalendarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/calendarios")
@Tag(name = "Rotas Calendario")
@CrossOrigin(origins = "*")
public class CalendarioController {

    @Autowired
    CalendarioService calendarioService;

    @GetMapping("")
    @Operation(summary = "Listar calendários", description = "Responsável por listar todos os calendários")
    public List<Calendario> listarCalendarios() {
        return calendarioService.findAllCalendarios();
    }

    @GetMapping("/{uid}")
    @Operation(summary = "Buscar calendário por UID", description = "Responsável por buscar um único calendário pelo UID")
    public ResponseEntity<Calendario> listarCalendarioUnico(@PathVariable(value = "uid") String uid) {
        Calendario calendario = calendarioService.buscarPorUid(uid);
        if (calendario != null) {
            return ResponseEntity.ok(calendario);
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found
        }
    }

    @PostMapping("")
    @Operation(summary = "Criar um novo calendário", description = "Responsável por criar um novo calendário")
    public Calendario criarCalendario(@RequestBody Calendario calendario) {
        return calendarioService.criarCalendario(calendario);
    }

    @DeleteMapping("/{uid}")
    @Operation(summary = "Deletar calendário", description = "Responsável por deletar um calendário pelo UID")
    public void deletarCalendario(@PathVariable(value = "uid") String uid) {
        calendarioService.deletarCalendario(uid);
    }

    @PutMapping("")
    @Operation(summary = "Atualizar calendário", description = "Responsável por atualizar um calendário existente")
    public Calendario atualizarCalendario(@RequestBody Calendario calendario) {
        return calendarioService.atualizarCalendario(calendario);
    }
}
