package com.example.repaso_quizz1.Service;

import com.example.repaso_quizz1.DTOs.RequestFlightDTO;
import com.example.repaso_quizz1.Model.Flights;
import com.example.repaso_quizz1.Repository.FlightsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightService {
    private final FlightsRepository flightsRepository;
    public FlightService(FlightsRepository flightsRepository) {
        this.flightsRepository = flightsRepository;
    }

    public Flights createFlight(RequestFlightDTO request) {
        Flights existingFlight = flightsRepository.findByNumVuelo(request.getNumVuelo());

        if(existingFlight != null){
            throw new RuntimeException("El número de vuelo ya existe");
        }
        if(request.getHoraSalida().isAfter(request.getHoraLlegada())) {
            throw new RuntimeException(
                    "La hora de salida debe ser menor a llegada"
            );
        }
        Flights flight = new Flights();

        flight.setAirline(request.getAirline());

        flight.setNumVuelo(request.getNumVuelo());

        flight.setHoraSalida(request.getHoraSalida());

        flight.setHoraLlegada(request.getHoraLlegada());

        flight.setAsientosDisponibles(
                request.getAsientosDisponibles()
        );

        return flightsRepository.save(flight);
    }

    public List<Flights> searchByNumVuelo(String numVuelo){
        return flightsRepository.findByNumVueloContaining(numVuelo);
    }

    public List<Flights> searchByAirline(String airline){
        return flightsRepository.findByAirlineContaining(airline);
    }

    public List<Flights> searchByDateRange(LocalDateTime start, LocalDateTime end){
        return flightsRepository.findByHoraSalidaBetween(start, end);
    }
}
