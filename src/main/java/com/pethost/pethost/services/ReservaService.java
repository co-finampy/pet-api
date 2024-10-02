package com.pethost.pethost.services;

import com.pethost.pethost.domain.Reserva;
import com.pethost.pethost.exceptions.ReservaNotFoundException;
import com.pethost.pethost.exceptions.InvalidReservaException;
import com.pethost.pethost.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    // Método para listar todas as reservas
    public List<Reserva> findAllReservas() {
        return reservaRepository.findAll();
    }

    // Método para buscar uma reserva pelo UID
    public Reserva buscarPorUid(String uid) {
        Optional<Reserva> reserva = reservaRepository.findById(uid);
        if (reserva.isEmpty()) {
            throw new ReservaNotFoundException(); // Lança exceção se a reserva não for encontrada
        }
        return reserva.get();
    }

    // Método para criar uma nova reserva
    public Reserva criarReserva(Reserva reserva) {
        // Adicione validações aqui, se necessário
        if (reserva.getUidClient() == null || reserva.getUidAnfitriao() == null) {
            throw new InvalidReservaException(); // Lança exceção se os dados da reserva forem inválidos
        }
        return reservaRepository.save(reserva);
    }

    // Método para deletar uma reserva
    public void deletarReserva(String uid) {
        if (!reservaRepository.existsById(uid)) {
            throw new ReservaNotFoundException(); // Lança exceção se a reserva não existir
        }
        reservaRepository.deleteById(uid);
    }

    // Método para atualizar uma reserva
    public Reserva atualizarReserva(Reserva reserva) {
        if (!reservaRepository.existsById(reserva.getUid())) {
            throw new ReservaNotFoundException(); // Lança exceção se a reserva não existir
        }
        // Adicione validações aqui, se necessário
        if (reserva.getUidClient() == null || reserva.getUidAnfitriao() == null) {
            throw new InvalidReservaException(); // Lança exceção se os dados da reserva forem inválidos
        }
        return reservaRepository.save(reserva);
    }
}
