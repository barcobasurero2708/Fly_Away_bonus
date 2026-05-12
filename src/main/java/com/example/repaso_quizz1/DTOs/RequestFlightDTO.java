package com.example.repaso_quizz1.DTOs;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestFlightDTO {

    @NotNull
    @NotEmpty
    private String airline;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Z0-9]{1,6}$")
    private String numVuelo;

    @NotNull
    @Past
    private LocalDateTime horaSalida;

    @Future
    @NotNull
    private LocalDateTime horaLlegada;

    @NotNull
    @Min(1)
    private Integer asientosDisponibles;
}
