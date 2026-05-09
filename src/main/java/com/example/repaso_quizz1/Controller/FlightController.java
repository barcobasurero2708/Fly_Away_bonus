package com.example.repaso_quizz1.Controller;

import com.example.repaso_quizz1.DTOs.RequestFlightDTO;
import com.example.repaso_quizz1.DTOs.ResponseFlightDTO;
import com.example.repaso_quizz1.Model.Flights;
import com.example.repaso_quizz1.Service.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    private final FlightService flightService;
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseFlightDTO> createFlight(@Valid @RequestBody RequestFlightDTO request){
        return ResponseEntity.ok(flightService.createFlight(request));
    }

    @GetMapping("search/number")
    public ResponseEntity<ResponseFlightDTO> searchByNumVuelo(@RequestParam String numVuelo){
        return ResponseEntity.ok(flightService.searchByNumVuelo(numVuelo));
    }

    @GetMapping("search/airline")
    public ResponseEntity<List<ResponseFlightDTO>> searchByAirline(@RequestParam String airline){
        return ResponseEntity.ok(flightService.searchByAirline(airline));
    }

    @GetMapping("/search/date")
    public ResponseEntity<List<ResponseFlightDTO>> searchByDateRange(@RequestParam LocalDate start, @RequestParam LocalDate end){
        return ResponseEntity.ok(flightService.searchByDateRange(start, end));
    }
}
