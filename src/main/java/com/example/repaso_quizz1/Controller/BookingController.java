package com.example.repaso_quizz1.Controller;

import com.example.repaso_quizz1.DTOs.BookingResponseDTO;
import com.example.repaso_quizz1.DTOs.FlightBookRequestDTO;
import com.example.repaso_quizz1.DTOs.NewIdDTO;
import com.example.repaso_quizz1.Service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flights")
public class BookingController {
    private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public ResponseEntity<NewIdDTO> bookFlight(@Valid @RequestBody FlightBookRequestDTO request) {
        return ResponseEntity.ok(bookingService.bookFlight(request));
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }
}
