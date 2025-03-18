package com.pethost.pethost.controllers;

import com.pethost.pethost.dtos.PetDTO;
import com.pethost.pethost.services.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pets")
@Tag(name = "Rotas de Pets")
@CrossOrigin(origins = "*")
public class PetsController {

    private static final Logger log = LoggerFactory.getLogger(PetsController.class);
    private final PetService petService;

    public PetsController(PetService petService) {
        this.petService = petService;
    }

    // ✅ Listar todos os pets
    @GetMapping("/listar")
    @Operation(summary = "Listar pets", description = "Lista todos os pets cadastrados")
    public ResponseEntity<List<PetDTO>> listarPets() {
        log.info("📌 Solicitada listagem de todos os pets");
        List<PetDTO> pets = petService.findAllPets();
        return pets.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pets);
    }

    // ✅ Buscar um pet por ID
    @GetMapping("/buscar/{id}")
    @Operation(summary = "Buscar pet por ID", description = "Busca um pet pelo ID")
    public ResponseEntity<PetDTO> listarPetUnico(@PathVariable(value = "id") long id) {
        log.info("📌 Buscando pet com ID: {}", id);
        return ResponseEntity.ok(petService.buscarPorId(id));
    }

    // ✅ Criar um novo pet vinculado ao usuário autenticado
    @PostMapping("/criar")
    @Operation(summary = "Criar pet", description = "Cria um novo pet automaticamente vinculado ao usuário autenticado")
    public ResponseEntity<PetDTO> criarPet(@RequestBody PetDTO petDTO) {
        String emailUsuario = getEmailUsuarioAutenticado();

        if (emailUsuario == null) {
            log.error("❌ Tentativa de criar pet sem autenticação!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        log.info("📌 Criando pet para usuário: {}", emailUsuario);
        PetDTO createdPet = petService.criarPet(petDTO, emailUsuario);

        if (createdPet == null) {
            log.error("❌ Erro ao criar pet para usuário: {}", emailUsuario);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        log.info("✅ Pet criado com sucesso! Nome: {}", createdPet.getNomePet());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPet);
    }

    // ✅ Atualizar um pet existente
    @PutMapping("/atualizar")
    @Operation(summary = "Atualizar pet", description = "Atualiza um pet existente")
    public ResponseEntity<PetDTO> atualizarPets(@RequestBody PetDTO petDTO) {
        log.info("📌 Atualizando pet ID: {}", petDTO.getId());
        return ResponseEntity.ok(petService.atualizarPet(petDTO));
    }

    // ✅ Deletar um pet por ID
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar pet", description = "Deleta um pet pelo ID")
    public ResponseEntity<String> deletarPets(@PathVariable(value = "id") long id) {
        log.info("📌 Solicitada exclusão do pet ID: {}", id);
        boolean isDeleted = petService.deletarPet(id);
        return isDeleted
                ? ResponseEntity.ok("✅ Pet deletado com sucesso.")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Pet não encontrado.");
    }

    // ✅ Buscar pets do usuário autenticado
    @GetMapping("/buscar/meus-pets")
    @Operation(summary = "Buscar pets do usuário autenticado", description = "Lista todos os pets cadastrados pelo usuário autenticado")
    public ResponseEntity<List<PetDTO>> buscarMeusPets() {
        String emailUsuario = getEmailUsuarioAutenticado();

        if (emailUsuario == null) {
            log.error("❌ Tentativa de buscar pets sem autenticação!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        log.info("📌 Buscando pets do usuário: {}", emailUsuario);
        List<PetDTO> pets = petService.buscarPetsPorUsuario(emailUsuario);
        return pets.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(pets);
    }

    // ✅ Obtém o e-mail do usuário autenticado via JWT
    private String getEmailUsuarioAutenticado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            return userDetails.getUsername(); // ✅ Retorna o e-mail do usuário autenticado
        }
        return null;
    }
}
