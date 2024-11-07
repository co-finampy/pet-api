package com.pethost.pethost.repositories;

import com.pethost.pethost.domain.Pet;
import com.pethost.pethost.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, String> {

    // Método para encontrar reservas por UID do cliente
    @Query("SELECT p FROM Reserva p WHERE p.usuarioClient.uid = :uid")
    List<Reserva> findByUidClient(@Param("uid") String uid);

}
