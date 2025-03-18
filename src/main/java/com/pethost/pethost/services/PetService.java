package com.pethost.pethost.services;

import com.pethost.pethost.domain.Pet;
import com.pethost.pethost.domain.Usuario;
import com.pethost.pethost.dtos.PetDTO;
import com.pethost.pethost.repositories.PetRepository;
import com.pethost.pethost.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetService {

    private static final Logger log = LoggerFactory.getLogger(PetService.class);

    private final PetRepository petRepository;
    private final UsuarioRepository usuarioRepository;

    // ✅ Criar um novo pet vinculado ao usuário autenticado
    @Transactional
    public PetDTO criarPet(PetDTO petDTO, String emailUsuario) {
        log.info("📌 Iniciando criação de pet para usuário: {}", emailUsuario);

        Usuario usuario = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> {
                    log.error("❌ Usuário não encontrado! E-mail: {}", emailUsuario);
                    return new RuntimeException("Usuário não encontrado! E-mail: " + emailUsuario);
                });

        Pet pet = new Pet();
        pet.setTipoPet(petDTO.getTipoPet());
        pet.setNomePet(petDTO.getNomePet());
        pet.setRaca(petDTO.getRaca());
        pet.setGenero(petDTO.getGenero());
        pet.setTamanho(petDTO.getTamanho());
        pet.setDataNascimento(petDTO.getDataNascimento());
        pet.setVacina(petDTO.isVacina());
        pet.setCastrado(petDTO.isCastrado());
        pet.setFoto(petDTO.getFoto());
        pet.setCriadoEm(LocalDateTime.now());
        pet.setUsuario(usuario);

        Pet petSalvo = petRepository.save(pet);
        log.info("✅ Pet criado com sucesso! ID: {}", petSalvo.getId());

        return new PetDTO(petSalvo);
    }

    // ✅ Buscar todos os pets
    public List<PetDTO> findAllPets() {
        log.info("📌 Buscando todos os pets cadastrados...");
        List<PetDTO> pets = petRepository.findAll().stream()
                .map(PetDTO::new)
                .collect(Collectors.toList());
        log.info("✅ {} pets encontrados!", pets.size());
        return pets;
    }

    // ✅ Buscar pets pelo e-mail do usuário autenticado
    public List<PetDTO> buscarPetsPorUsuario(String emailUsuario) {
        log.info("📌 Buscando pets do usuário: {}", emailUsuario);
        List<PetDTO> pets = petRepository.findByUsuarioEmail(emailUsuario).stream()
                .map(PetDTO::new)
                .collect(Collectors.toList());
        log.info("✅ {} pets encontrados para o usuário: {}", pets.size(), emailUsuario);
        return pets;
    }

    // ✅ Atualizar um pet existente
    @Transactional
    public PetDTO atualizarPet(PetDTO petDTO) {
        log.info("📌 Atualizando pet ID: {}", petDTO.getId());

        Pet pet = petRepository.findById(petDTO.getId())
                .orElseThrow(() -> {
                    log.error("❌ Pet não encontrado para atualização! ID: {}", petDTO.getId());
                    return new RuntimeException("Pet não encontrado!");
                });

        pet.setTipoPet(petDTO.getTipoPet());
        pet.setNomePet(petDTO.getNomePet());
        pet.setRaca(petDTO.getRaca());
        pet.setGenero(petDTO.getGenero());
        pet.setTamanho(petDTO.getTamanho());
        pet.setDataNascimento(petDTO.getDataNascimento());
        pet.setVacina(petDTO.isVacina());
        pet.setCastrado(petDTO.isCastrado());
        pet.setFoto(petDTO.getFoto());

        Pet petAtualizado = petRepository.save(pet);
        log.info("✅ Pet atualizado com sucesso! ID: {}", petAtualizado.getId());

        return new PetDTO(petAtualizado);
    }

    // ✅ Deletar um pet por ID
    @Transactional
    public boolean deletarPet(long id) {
        log.info("📌 Tentando deletar pet ID: {}", id);

        if (!petRepository.existsById(id)) {
            log.error("❌ Pet não encontrado para exclusão! ID: {}", id);
            return false;
        }

        petRepository.deleteById(id);
        log.info("✅ Pet deletado com sucesso! ID: {}", id);
        return true;
    }

    // ✅ Buscar pet por ID
    public PetDTO buscarPorId(long id) {
        log.info("📌 Buscando pet por ID: {}", id);

        PetDTO petDTO = petRepository.findById(id)
                .map(PetDTO::new)
                .orElseThrow(() -> {
                    log.error("❌ Pet não encontrado! ID: {}", id);
                    return new RuntimeException("Pet não encontrado!");
                });

        log.info("✅ Pet encontrado! ID: {}", petDTO.getId());
        return petDTO;
    }
}
