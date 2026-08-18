package com.demovete.veterinariabackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SpeciesCreateRequestDTO (
        @NotBlank(message = "El nombre de la especie es obligatorio.")
        @Size(max = 50, message = "El nombre no puede superar los 50 caracteres.")
        String name,

        @Size(max = 255, message = "La descripcion no puede superar los 255 caracteres")
        String description
){
}
