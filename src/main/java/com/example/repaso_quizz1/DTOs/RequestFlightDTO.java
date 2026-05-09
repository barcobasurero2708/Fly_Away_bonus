package com.example.repaso_quizz1.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class RequestFlightDTO {

    @NotNull
    @NotEmpty
    private String airline;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Z0-9]{1,6}$")
    private String numVuelo;

    @NotNull
    private LocalDate horaSalida;

    @NotNull
    private LocalDate horaLlegada;

    @NotNull
    @Min(1)
    private Integer asientosDisponibles;
}
