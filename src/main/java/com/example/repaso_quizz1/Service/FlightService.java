package com.example.repaso_quizz1.Service;

import com.example.repaso_quizz1.DTOs.RequestFlightDTO;
import com.example.repaso_quizz1.DTOs.ResponseFlightDTO;
import com.example.repaso_quizz1.Model.Flights;
import com.example.repaso_quizz1.Repository.FlightsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlightService {
    private final FlightsRepository flightsRepository;
    public FlightService(FlightsRepository flightsRepository) {
        this.flightsRepository = flightsRepository;
    }

    private ResponseFlightDTO toResponseFlightDTO(Flights flight) {
        return new ResponseFlightDTO(
                flight.getAirline(),
                flight.getNumVuelo(),
                flight.getHoraSalida().toString(),
                flight.getHoraLlegada().toString(),
                flight.getAsientosDisponibles()
        );
    }

    public ResponseFlightDTO createFlight(RequestFlightDTO request) {
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

        Flights savedFlight = flightsRepository.save(flight);

        return toResponseFlightDTO(savedFlight);
    }

    public ResponseFlightDTO searchByNumVuelo(String numVuelo){
        Flights flight = flightsRepository.findByNumVuelo(numVuelo);

        if (flight == null) {
            throw new RuntimeException("Vuelo no encontrado");
        }

        return toResponseFlightDTO(flight);
    }

    public List<ResponseFlightDTO> searchByAirline(String airline){
        return flightsRepository.findByAirlineContaining(airline)
                .stream()
                .map(this::toResponseFlightDTO)
                .toList();
    }

    public List<ResponseFlightDTO> searchByDateRange(LocalDate start, LocalDate end){
        return flightsRepository.findByHoraSalidaBetween(start, end)
                .stream()
                .map(this::toResponseFlightDTO)
                .toList();
    }
}
