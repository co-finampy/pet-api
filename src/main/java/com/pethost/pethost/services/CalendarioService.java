package com.pethost.pethost.services;

import com.finampy.pethost.domain.Calendario;
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
    public Calendario buscarPorUid(String uid) {
        Optional<Calendario> calendario = calendarioRepository.findById(uid);
        return calendario.orElse(null);  // Retorna o calendário ou null se não for encontrado
    }

    // Método para criar um novo calendário
    public Calendario criarCalendario(Calendario calendario) {
        return calendarioRepository.save(calendario);
    }

    // Método para deletar um calendário
    public void deletarCalendario(String uid) {
        calendarioRepository.deleteById(uid);
    }

    // Método para atualizar um calendário
    public Calendario atualizarCalendario(Calendario calendario) {
        return calendarioRepository.save(calendario);
    }
}
