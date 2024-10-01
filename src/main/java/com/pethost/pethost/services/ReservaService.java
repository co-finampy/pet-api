package com.pethost.pethost.services;

import com.pethost.pethost.domain.Reserva;
import com.pethost.pethost.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;


    public List<Reserva> findAllReservas() {
        return reservaRepository.findAll();
    }


    public Reserva buscarPorUid(Long id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        return reserva.orElse(null);  // Retorna a reserva ou null se não for encontrada
    }

    // Método para criar uma nova reserva
    public Reserva criarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    // Método para deletar uma reserva
    public void deletarReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    // Método para atualizar uma reserva
    public Reserva atualizarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }
}
