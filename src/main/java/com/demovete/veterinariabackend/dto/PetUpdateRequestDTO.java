package com.demovete.veterinariabackend.dto;

import com.demovete.veterinariabackend.model.enums.Gender;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PetUpdateRequestDTO (
        @Size(max = 60, message = "El nombre de la mascota no puede superar los 60 caracteres")
        String name,

        @PastOrPresent(message = "La fecha de nacimiento no puede ser una fecha futura")
        LocalDate birthDate,

        Gender gender,
        @Size(max = 15, message = "El número de microchip no puede superar los 15 digitos")
        String microchipNumber,

        Boolean neutered,

        @PastOrPresent(message = "La fecha de esterilización no puede ser una fecha futura")
        LocalDate neuteringDate,

        @Size(max = 30, message = "El color no puede superar los 30 caracteres")
        String color,

        @Size(max = 255, message = "Las marcas características de la mascota no puede superar los 255 caracteres")
        String distinctiveMark,

        String photoUrl,
        Boolean active,
        String notes,
        Long breedId,
        Long customerId
)
{
}
