package com.demovete.veterinariabackend.dto;

public record BreedResponseDTO(
        Long id,
        String name,
        Long speciesId,
        String speciesName
) {
}
