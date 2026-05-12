package com.example.repaso_quizz1.Service;

import com.example.repaso_quizz1.DTOs.BookingResponseDTO;
import com.example.repaso_quizz1.DTOs.FlightBookRequestDTO;
import com.example.repaso_quizz1.DTOs.NewIdDTO;
import com.example.repaso_quizz1.Exceptions.ResourceNotFoundException;
import com.example.repaso_quizz1.Model.Booking;
import com.example.repaso_quizz1.Model.Flight;
import com.example.repaso_quizz1.Model.User;
import com.example.repaso_quizz1.Repository.BookingRepository;
import com.example.repaso_quizz1.Repository.FlightsRepository;
import com.example.repaso_quizz1.Repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class BookingService {
    private final FlightsRepository flightsRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    public BookingService(FlightsRepository flightsRepository, BookingRepository bookingRepository, UserRepository userRepository) {
        this.flightsRepository = flightsRepository;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    public NewIdDTO bookFlight(FlightBookRequestDTO request) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        Flight flight = flightsRepository.findById(request.getFlightId()).orElseThrow(() -> new ResourceNotFoundException("Flight not found"));
        if (flight.getAsientosDisponibles() <= 0) {
            throw new RuntimeException(
                    "No seats available"
            );
        }
        flight.setAsientosDisponibles(flight.getAsientosDisponibles() - 1);
        flightsRepository.save(flight);
        Booking booking = new Booking();
        booking.setBookingDate(LocalDateTime.now());
        booking.setFlight(flight);
        booking.setUser(user);
        Booking savedBooking = bookingRepository.save(booking);
        return new NewIdDTO(savedBooking.getId());
    }

    public BookingResponseDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        BookingResponseDTO response = new BookingResponseDTO();
        response.setId(booking.getId());
        response.setBookingDate(booking.getBookingDate());
        response.setFlightId(booking.getFlight().getId());
        response.setFlightNumber(booking.getFlight().getNumVuelo());
        response.setCustomerId(booking.getUser().getId());
        response.setCustomerFirstName(booking.getUser().getNombre());
        response.setCustomerLastName(booking.getUser().getApellido());
        return response;
    }
}
