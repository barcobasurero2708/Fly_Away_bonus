package com.example.repaso_quizz1.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RequestFlightDTO {
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Z0-9]{1,6}$")
    private String numVuelo;

    @NotNull
    @NotEmpty
    private LocalDate horaSalida;

    @NotNull
    @NotEmpty
    private LocalDate horaLlegada;

    @NotNull
    @NotEmpty
    @Min(0)
    private Integer asientosDisponibles;
}
