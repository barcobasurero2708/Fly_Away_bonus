package com.example.repaso_quizz1.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class RequestUserId {
    @NotNull
    @NotEmpty
    private String nombre;

    @NotNull
    @NotEmpty
    private String apellido;
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Z0-9]{8,12}$")
    private String password;
}
