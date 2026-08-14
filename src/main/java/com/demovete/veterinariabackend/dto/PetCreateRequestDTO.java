package com.demovete.veterinariabackend.dto;

import com.demovete.veterinariabackend.model.enums.Gender;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PetCreateRequestDTO (
        @NotBlank(message = "El nombre de la mascota es obligatoria")
        @Size(max = 60, message = "El nombre no puede superar los 60 caracteres")
        String name,

        @PastOrPresent(message = "La fecha de nacimiento no puede ser una fecha futura")
        LocalDate birthDate,

        Gender gender,
        String microchipNumber,
        Boolean neutered,
        LocalDate neuteringDate,
        String color,
        String distinctiveMarks,
        String photoUrl,
        String notes,

        @NotNull(message = "Debes seleccionar una raza para la mascota")
        Long breedId,
        @NotNull(message = "Debes asociar la mascota a un cliente/dueño")
        Long customerId
) {

}
