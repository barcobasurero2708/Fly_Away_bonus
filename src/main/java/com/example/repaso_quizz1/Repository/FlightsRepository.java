package com.example.repaso_quizz1.Repository;

import com.example.repaso_quizz1.Model.Flights;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightsRepository extends CrudRepository<Flights, Long> {

    List<Flights> findByNumVueloContaining(String numVuelo);

    List<Flights> findByAirlineContaining(String airline);

    List<Flights> findByHoraSalidaBetween(LocalDateTime start, LocalDateTime end);

    Flights findByNumVuelo(String numVuelo);
}
