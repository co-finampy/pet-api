package com.pethost.pethost.services;

import com.pethost.pethost.domain.Reserva;
import com.pethost.pethost.dtos.ReservaResponseDto;
import com.pethost.pethost.exceptions.ReservaNotFoundException;
import com.pethost.pethost.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    // Método para buscar todas as reservas
    public List<Reserva> findAllReservas() {
        return reservaRepository.findAll();
    }

    // Método para buscar uma reserva pelo UID
    public Reserva buscarPorUid(String uid) {
        return reservaRepository.findById(uid)
                .orElseThrow(() -> new ReservaNotFoundException("Reserva não encontrada para o UID: " + uid));
    }

    // Método para criar uma nova reserva
    public Reserva criarReserva(Reserva reserva) {
        // Aqui você pode adicionar lógica extra se necessário antes de salvar a reserva
        return reservaRepository.save(reserva);
    }

    // Método para deletar uma reserva pelo UID
    public void deletarReserva(String uid) {
        if (!reservaRepository.existsById(uid)) {
            throw new ReservaNotFoundException("Reserva não encontrada para o UID: " + uid);
        }
        reservaRepository.deleteById(uid);
    }

    // Método para atualizar uma reserva
    public Reserva atualizarReserva(Reserva reserva) {
        if (!reservaRepository.existsById(reserva.getUid())) {
            throw new ReservaNotFoundException("Reserva não encontrada para o UID: " + reserva.getUid());
        }
        return reservaRepository.save(reserva);
    }

    // Método para buscar reservas de um usuário por seu UID
    public List<ReservaResponseDto> buscarReservasPorUserUid(String uid) {
        List<Reserva> reservas = reservaRepository.findByUidClient(uid);
        if (reservas.isEmpty()) {
            throw new ReservaNotFoundException("Nenhuma reserva encontrada para o usuário com UID: " + uid);
        }
        return reservas.stream()
                .map(reserva -> new ReservaResponseDto(
                        reserva.getUid(),
                        reserva.getUsuarioClient().getUid(),
                        reserva.getUsuarioAnfitriao().getUid(),
                        reserva.getUidPet(),
                        reserva.getDataEntrada().toString(),
                        reserva.getDataSaida().toString(),
                        reserva.getTipoReserva(),
                        reserva.getValor(),
                        reserva.getStatus(),
                        reserva.getCreatedAt().toString()))
                .collect(Collectors.toList());
    }
}
