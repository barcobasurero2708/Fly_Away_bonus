package com.example.repaso_quizz1.Service;

import com.example.repaso_quizz1.DTOs.RequestFlightDTO;
import com.example.repaso_quizz1.DTOs.ResponseFlightDTO;
import com.example.repaso_quizz1.Model.Flight;
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

    private ResponseFlightDTO toResponseFlightDTO(Flight flight) {
        return new ResponseFlightDTO(
                flight.getAirline(),
                flight.getNumVuelo(),
                flight.getHoraSalida(),
                flight.getHoraLlegada(),
                flight.getAsientosDisponibles()
        );
    }

    public ResponseFlightDTO createFlight(RequestFlightDTO request) {
        Flight existingFlight = flightsRepository.findByNumVuelo(request.getNumVuelo());

        if(existingFlight != null){
            throw new RuntimeException("El número de vuelo ya existe");
        }
        if(request.getHoraSalida().isAfter(request.getHoraLlegada())) {
            throw new RuntimeException(
                    "La hora de salida debe ser menor a llegada"
            );
        }
        Flight flight = new Flight();

        flight.setAirline(request.getAirline());

        flight.setNumVuelo(request.getNumVuelo());

        flight.setHoraSalida(request.getHoraSalida());

        flight.setHoraLlegada(request.getHoraLlegada());

        flight.setAsientosDisponibles(
                request.getAsientosDisponibles()
        );

        Flight savedFlight = flightsRepository.save(flight);

        return toResponseFlightDTO(savedFlight);
    }

    public List<ResponseFlightDTO> search(
            String flightNumber,
            String airlineName,
            LocalDateTime estDepartureTimeFrom,
            LocalDateTime estDepartureTimeTo
    ) {
        if (flightNumber != null) {
            return flightsRepository
                    .findByNumVueloContaining(flightNumber)
                    .stream()
                    .map(this::toResponseFlightDTO)
                    .toList();
        }


        if (airlineName != null) {
            return flightsRepository
                    .findByAirlineContaining(airlineName)
                    .stream()
                    .map(this::toResponseFlightDTO)
                    .toList();
        }

        if (
                estDepartureTimeFrom != null
                        &&
                        estDepartureTimeTo != null
        ) {

            return flightsRepository
                    .findByHoraSalidaBetween(
                            estDepartureTimeFrom,
                            estDepartureTimeTo
                    )
                    .stream()
                    .map(this::toResponseFlightDTO)
                    .toList();
        }

        return List.of();
    }

    public ResponseFlightDTO getFlightById(Long id) {
        Flight flight = flightsRepository.findById(id).orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));
        return toResponseFlightDTO(flight);
    }
}
