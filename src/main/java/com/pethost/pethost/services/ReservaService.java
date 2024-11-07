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

    public List<Reserva> findAllReservas() {
        return reservaRepository.findAll();
    }

    public Reserva buscarPorUid(String uid) {
        return reservaRepository.findById(uid)
                .orElseThrow(ReservaNotFoundException::new);
    }

    public Reserva criarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public void deletarReserva(String uid) {
        if (!reservaRepository.existsById(uid)) {
            throw new ReservaNotFoundException();
        }
        reservaRepository.deleteById(uid);
    }

    public Reserva atualizarReserva(Reserva reserva) {
        if (!reservaRepository.existsById(reserva.getUid())) {
            throw new ReservaNotFoundException();
        }
        return reservaRepository.save(reserva);
    }

    public List<ReservaResponseDto> buscarReservasPorUserUid(Long uidClient) {
        List<Reserva> reservas = reservaRepository.findByUidClient(uidClient);
        if (reservas.isEmpty()) {
            throw new ReservaNotFoundException();
        }
        return reservas.stream()
                .map(reserva -> new ReservaResponseDto(
                        reserva.getUid(),
                        reserva.getUsuarioClient().getUid(),
                        reserva.getUsuarioAnfitriao().getUid(),
                        reserva.getUidPet(),
                        reserva.getDataEntrada(),
                        reserva.getDataSaida(),
                        reserva.getTipoReserva(),
                        reserva.getValor(),
                        reserva.getStatus(),
                        reserva.getCreatedAt()))
                .collect(Collectors.toList());
    }
}
