package com.demovete.veterinariabackend.dto;

public record CustomerResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String documentNumber,
        String photoUrl,
        Boolean active
) {
}
