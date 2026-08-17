package com.demovete.veterinariabackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record CustomerUpdateRequestDTO (
        @Size(max = 60, message = "El nombre no puede superar los 60 caracteres")
        String firstName,

        @Size(max = 60, message = "El apellido no puede superar los 60 caracteres")
        String lastName,

        @Email(message = "El formato del correo electrónico es inválido")
        @Size(max = 150, message = "El email no puede superar los 150 caracteres")
        String email,

        @Size(max = 30, message = "El teléfono no puede superar los 30 caracteres")
        String phone,

        @Size(max = 20, message = "El documento no puede superar los 20 caracteres")
        String documentNumber,

        String photoUrl,
        Boolean active
){
}
