package com.example.repaso_quizz1.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO {
    private Long id;

    private LocalDateTime bookingDate;

    private Long flightId;

    private String flightNumber;

    private Long customerId;

    private String customerFirstName;

    private String customerLastName;
}
