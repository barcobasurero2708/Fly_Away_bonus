package com.example.repaso_quizz1.Repository;

import com.example.repaso_quizz1.Model.Flights;
import org.springframework.data.repository.CrudRepository;

public interface FlightsRepository extends CrudRepository<Flights, Long> {
    Flights findByNumVuelo(String numVuelo);
}
