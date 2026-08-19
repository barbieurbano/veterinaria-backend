package com.demovete.veterinariabackend.dto;

import jakarta.validation.constraints.Size;

public record BreedUpdateRequestDTO(
        @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
        String name
) {
}
