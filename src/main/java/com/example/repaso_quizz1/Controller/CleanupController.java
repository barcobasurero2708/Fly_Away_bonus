package com.example.repaso_quizz1.Controller;


import com.example.repaso_quizz1.Repository.BookingRepository;
import com.example.repaso_quizz1.Repository.FlightsRepository;
import com.example.repaso_quizz1.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

public class CleanupController {
    private final BookingRepository bookingRepository;

    private final FlightsRepository flightsRepository;

    private final UserRepository userRepository;

    public CleanupController(
            BookingRepository bookingRepository,
            FlightsRepository flightsRepository,
            UserRepository userRepository
    ) {

        this.bookingRepository = bookingRepository;
        this.flightsRepository = flightsRepository;
        this.userRepository = userRepository;
    }

    @DeleteMapping
    public ResponseEntity<Void> cleanup() {
        bookingRepository.deleteAll();
        flightsRepository.deleteAll();
        userRepository.deleteAll();
        return ResponseEntity.ok().build();
    }
}
