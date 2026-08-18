package com.demovete.veterinariabackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BreedCreateRequestDTO(
        @NotBlank(message = "El nombre de la raza es obligatorio")
        @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
        String name,

        @NotNull(message = "Debes indicar a qué especie pertenece la raza")
        Long speciesId
) {
}
