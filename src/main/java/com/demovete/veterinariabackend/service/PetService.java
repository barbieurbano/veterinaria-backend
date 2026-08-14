package com.demovete.veterinariabackend.service;

import com.demovete.veterinariabackend.dto.PetCreateRequestDTO;
import com.demovete.veterinariabackend.dto.PetResponseDTO;
import com.demovete.veterinariabackend.dto.PetUpdateRequestDTO;

import java.util.List;

public interface PetService {
    PetResponseDTO createPet(PetCreateRequestDTO dto);
    PetResponseDTO updatePet(Long id, PetUpdateRequestDTO dto);
    PetResponseDTO getPetById(Long id);
    List<PetResponseDTO> getPetsByCustomerId(Long customerId);

    //Borrado unicamente logico.
    void deletePet(Long id);
}
