package com.example.repaso_quizz1.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFlightDTO {
    @NotNull
    private String airline;

    @NotNull
    private String numVuelo;;

    @NotNull
    private LocalDateTime horaSalida;

    @NotNull
    private LocalDateTime horaLlegada;

    @NotNull
    private Integer asientosDisponibles;
}
