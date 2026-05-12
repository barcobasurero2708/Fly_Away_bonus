package com.example.repaso_quizz1.Repository;

import com.example.repaso_quizz1.Model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightsRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByAirlineContaining(String airline);

    List<Flight> findByNumVueloContaining(String numeroVuelo);

    List<Flight> findByHoraSalidaBetween(LocalDateTime start, LocalDateTime end);

    Flight findByNumVuelo(String numVuelo);
}
