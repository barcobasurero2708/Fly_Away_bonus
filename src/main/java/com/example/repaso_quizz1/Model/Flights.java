package com.example.repaso_quizz1.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "flights")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flights {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numVuelo;

    @Column(nullable = false)
    private LocalDate horaSalida;

    @Column(nullable = false)
    private LocalDate horaLlegada;

    @Column(nullable = false)
    private Integer asientosDisponibles;
}
