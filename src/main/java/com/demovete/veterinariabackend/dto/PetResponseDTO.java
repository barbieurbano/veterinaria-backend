package com.demovete.veterinariabackend.dto;

import com.demovete.veterinariabackend.model.enums.Gender;

import java.time.LocalDate;

public record PetResponseDTO(
        Long id,
        String name,
        LocalDate birthDate,
        Gender gender,
        String microchipNumber,
        Boolean neutered,
        LocalDate neuteringDate,
        String color,
        String distinctiveMarks,
        String photoUrl,
        Boolean active,
        String notes,

        Long breedId,
        String breedName,
        String speciesName,

        Long customerId,
        String customerFullname

) {
}
