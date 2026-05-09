package com.example.repaso_quizz1.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserDTO {
    @Email
    private String username;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]+$")
    private String nombre;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]+$")
    private String apellido;


    @Email
    private String email;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Z0-9]{8,12}$")
    private String password;
}
