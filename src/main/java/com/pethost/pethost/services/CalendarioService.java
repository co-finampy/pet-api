package com.pethost.pethost.services;

import com.pethost.pethost.domain.Calendario;
import com.pethost.pethost.dtos.CalendarioDisponibilidadeDto;
import com.pethost.pethost.exceptions.CalendarioNotFoundException;
import com.pethost.pethost.exceptions.InvalidCalendarioException;
import com.pethost.pethost.repositories.CalendarioRepository;
import com.pethost.pethost.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CalendarioService {

    @Autowired
    private CalendarioRepository calendarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // ✅ Listar todos os calendários
    public List<Calendario> findAllCalendarios() {
        return calendarioRepository.findAll();
    }

    // ✅ Buscar calendário pelo UID do anfitrião
    public Calendario buscarPorAnfitriao(String uidAnfitriao) {
        return calendarioRepository.findByUsuarioUid(uidAnfitriao)
                .orElseThrow(() -> new CalendarioNotFoundException());
    }

    // ✅ Criar um novo calendário
    public Calendario criarCalendario(Calendario calendario) {
        if (calendario == null || calendario.getUsuario() == null) {
            throw new InvalidCalendarioException();
        }
        return calendarioRepository.save(calendario);
    }

    // ✅ Atualizar um calendário existente
    public Calendario atualizarCalendario(Calendario calendario) {
        if (!calendarioRepository.existsById(calendario.getUid())) {
            throw new CalendarioNotFoundException();
        }
        return calendarioRepository.save(calendario);
    }

    // ✅ Atualizar **apenas** as datas disponíveis do anfitrião
    public Calendario atualizarDatasDisponiveis(CalendarioDisponibilidadeDto dto) {
        // 🔍 Buscar o calendário pelo UID do anfitrião
        Optional<Calendario> optionalCalendario = calendarioRepository.findByUsuarioUid(dto.getUidAnfitriao());

        if (optionalCalendario.isEmpty()) {
            throw new CalendarioNotFoundException();
        }

        // ✅ Atualiza **apenas** as datas disponíveis
        Calendario calendario = optionalCalendario.get();
        calendario.setDias(dto.getDias());

        return calendarioRepository.save(calendario);
    }

    // ✅ Deletar um calendário pelo UID
    public void deletarCalendario(Long uid) {
        if (!calendarioRepository.existsById(uid)) {
            throw new CalendarioNotFoundException();
        }
        calendarioRepository.deleteById(uid);
    }
}
