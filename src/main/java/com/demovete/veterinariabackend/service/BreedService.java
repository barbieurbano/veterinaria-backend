package com.demovete.veterinariabackend.service;

import com.demovete.veterinariabackend.dto.BreedCreateRequestDTO;
import com.demovete.veterinariabackend.dto.BreedResponseDTO;
import com.demovete.veterinariabackend.dto.BreedUpdateRequestDTO;

import java.util.List;

public interface BreedService {
    BreedResponseDTO createBreed(BreedCreateRequestDTO dto);
    BreedResponseDTO getBreedById(Long id);
    List<BreedResponseDTO> getAllBreeds();
    List<BreedResponseDTO> getBreedsBySpeciesId(Long speciesId);
    BreedResponseDTO updateBreed(Long id, BreedUpdateRequestDTO dto);
    void deleteBreed(Long id);
}
