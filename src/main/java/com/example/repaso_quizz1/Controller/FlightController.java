package com.example.repaso_quizz1.Controller;

import com.example.repaso_quizz1.DTOs.RequestFlightDTO;
import com.example.repaso_quizz1.DTOs.ResponseFlightDTO;
import com.example.repaso_quizz1.Service.FlightService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/search")
    public ResponseEntity<List<ResponseFlightDTO>> search(
            @RequestParam(required = false)
            String flightNumber,

            @RequestParam(required = false)
            String airlineName,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime estDepartureTimeFrom,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime estDepartureTimeTo
    ) {

        return ResponseEntity.ok(flightService.search(flightNumber, airlineName, estDepartureTimeFrom, estDepartureTimeTo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseFlightDTO> getFlightById(@PathVariable Long id) {
        return ResponseEntity.ok(flightService.getFlightById(id));
    }
}
