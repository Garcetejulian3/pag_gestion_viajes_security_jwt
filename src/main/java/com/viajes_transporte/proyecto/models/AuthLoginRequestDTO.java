package com.viajes_transporte.proyecto.models;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequestDTO(@NotBlank String username,@NotBlank String password) {

}
