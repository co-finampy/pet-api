package com.pethost.pethost.controllers;

import com.pethost.pethost.domain.Calendario;
import com.pethost.pethost.dtos.CalendarioDisponibilidadeDto;
import com.pethost.pethost.services.CalendarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/calendarios")
@Tag(name = "Rotas Calendário")
@CrossOrigin(origins = "*")
public class CalendarioController {

    @Autowired
    private CalendarioService calendarioService;

    // ✅ Listar todos os calendários disponíveis
    @GetMapping("")
    @Operation(summary = "Listar calendários", description = "Lista todos os calendários cadastrados no sistema")
    public ResponseEntity<List<Calendario>> listarCalendarios() {
        List<Calendario> calendarios = calendarioService.findAllCalendarios();
        return ResponseEntity.ok(calendarios);
    }

    // ✅ Buscar calendário por UID do anfitrião
    @GetMapping("/{uidAnfitriao}")
    @Operation(summary = "Buscar calendário por UID do anfitrião", description = "Retorna o calendário de um anfitrião específico")
    public ResponseEntity<Calendario> listarCalendarioPorAnfitriao(@PathVariable(value = "uidAnfitriao") String uidAnfitriao) {
        Calendario calendario = calendarioService.buscarPorAnfitriao(uidAnfitriao);
        return ResponseEntity.ok(calendario);
    }

    // ✅ Criar um novo calendário
    @PostMapping("")
    @Operation(summary = "Criar calendário", description = "Cria um novo calendário para um anfitrião")
    public ResponseEntity<Calendario> criarCalendario(@RequestBody Calendario calendario) {
        Calendario novoCalendario = calendarioService.criarCalendario(calendario);
        return ResponseEntity.ok(novoCalendario);
    }

    // ✅ Atualizar um calendário existente
    @PutMapping("/{uid}")
    @Operation(summary = "Atualizar calendário", description = "Atualiza um calendário já cadastrado")
    public ResponseEntity<Calendario> atualizarCalendario(@PathVariable Long uid, @RequestBody Calendario calendario) {
        calendario.setUid(uid);
        Calendario calendarioAtualizado = calendarioService.atualizarCalendario(calendario);
        return ResponseEntity.ok(calendarioAtualizado);
    }

    // ✅ Atualizar apenas as datas disponíveis do anfitrião
    @PutMapping("/atualizar-datas")
    @Operation(summary = "Atualizar datas disponíveis", description = "Permite que um anfitrião atualize suas datas disponíveis")
    public ResponseEntity<Calendario> atualizarDatasDisponiveis(@RequestBody CalendarioDisponibilidadeDto dto) {
        Calendario calendarioAtualizado = calendarioService.atualizarDatasDisponiveis(dto);
        return ResponseEntity.ok(calendarioAtualizado);
    }

    // ✅ Deletar um calendário pelo UID
    @DeleteMapping("/{uid}")
    @Operation(summary = "Deletar calendário", description = "Remove um calendário pelo UID informado")
    public ResponseEntity<Void> deletarCalendario(@PathVariable(value = "uid") Long uid) {
        calendarioService.deletarCalendario(uid);
        return ResponseEntity.noContent().build();
    }
}
