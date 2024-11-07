package com.pethost.pethost.repositories;

import com.pethost.pethost.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, String> {

    // Método para encontrar reservas por UID do cliente
    List<Reserva> findByUidClient(Long uidClient);
}
