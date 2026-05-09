package com.example.repaso_quizz1.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFlightDTO {
    @NotNull
    private String Airline;

    @NotNull
    private String NumVuelo;

    @NotNull
    private String HoraSalida;

    @NotNull
    private String HoraLlegada;

    @NotNull
    private Integer AsientosDisponibles;
}
