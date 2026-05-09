package com.example.repaso_quizz1.Repository;

import com.example.repaso_quizz1.Model.Flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightsRepository extends JpaRepository<Flights, Long> {

    List<Flights> findByAirlineContaining(String airline);

    List<Flights> findByHoraSalidaBetween(LocalDate start, LocalDate end);

    Flights findByNumVuelo(String numVuelo);
}
