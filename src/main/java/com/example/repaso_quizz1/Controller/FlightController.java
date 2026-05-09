package com.example.repaso_quizz1.Controller;

import com.example.repaso_quizz1.DTOs.RequestFlightDTO;
import com.example.repaso_quizz1.Model.Flights;
import com.example.repaso_quizz1.Service.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flights")
public class FlightController {
    private final FlightService flightService;
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/create")
    public ResponseEntity<Flights> createFlight(@Valid @RequestBody RequestFlightDTO request){
        return ResponseEntity.ok(flightService.createFlight(request));
    }

    @GetMapping("search/number")
    public ResponseEntity<Flights> searchByNumVuelo(@RequestParam String numVuelo){
        return ResponseEntity.ok(flightService.searchByNumVuelo(numVuelo).get(0));
    }

    @GetMapping("search/airline")
    public ResponseEntity<Flights> searchByAirline(@RequestParam String airline){
        return ResponseEntity.ok(flightService.searchByAirline(airline).get(0));
    }

}
