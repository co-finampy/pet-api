package com.pethost.pethost.repositories;

import com.pethost.pethost.domain.Reserva; // Ajuste o pacote de importação
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, String> {
}
