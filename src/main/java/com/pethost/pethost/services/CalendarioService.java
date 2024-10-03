package com.pethost.pethost.services;

import com.pethost.pethost.domain.Calendario;
import com.pethost.pethost.exceptions.CalendarioNotFoundException;
import com.pethost.pethost.exceptions.InvalidCalendarioException;
import com.pethost.pethost.repositories.CalendarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarioService {

    @Autowired
    private CalendarioRepository calendarioRepository;

    // Método para listar todos os calendários
    public List<Calendario> findAllCalendarios() {
        return calendarioRepository.findAll();
    }

    // Método para buscar um calendário pelo UID
    public Calendario buscarPorUid(Long uid) {
        String uidAsString = String.valueOf(uid);
        Optional<Calendario> calendario = calendarioRepository.findById(uidAsString);
        if (calendario.isEmpty()) {
            throw new CalendarioNotFoundException(); // Lança exceção se o calendário não for encontrado
        }
        return calendario.get();
    }

    // Método para criar um novo calendário
    public Calendario criarCalendario(Calendario calendario) {
        // Verificar se o calendário tem dados válidos
        if (calendario == null || calendario.getUid() == null) {
            throw new InvalidCalendarioException(); // Lança exceção se o calendário for inválido
        }
        return calendarioRepository.save(calendario);
    }

    // Método para deletar um calendário
    public void deletarCalendario(Long uid) {
        String uidAsString = String.valueOf(uid);
        if (!calendarioRepository.existsById(uidAsString)) {
            throw new CalendarioNotFoundException(); // Lança exceção se o calendário não existir
        }
        calendarioRepository.deleteById(uidAsString);
    }

    // Método para atualizar um calendário
    public Calendario atualizarCalendario(Calendario calendario) {
        String uidAsString = String.valueOf(calendario.getUid()); // Converte o ID para String
        if (!calendarioRepository.existsById(uidAsString)) {
            throw new CalendarioNotFoundException(); // Lança exceção se o calendário não existir
        }
        return calendarioRepository.save(calendario);
    }
}
