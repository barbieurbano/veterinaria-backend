package com.demovete.veterinariabackend.service;

import com.demovete.veterinariabackend.dto.SpeciesCreateRequestDTO;
import com.demovete.veterinariabackend.dto.SpeciesResponseDTO;
import com.demovete.veterinariabackend.dto.SpeciesUpdateRequestDTO;

import java.util.List;

public interface SpeciesService {
    SpeciesResponseDTO createSpecies(SpeciesCreateRequestDTO dto);
    SpeciesResponseDTO getSpeciesById(Long id);
    List<SpeciesResponseDTO> getAllSpecies();
    SpeciesResponseDTO updateSpecies(Long id, SpeciesUpdateRequestDTO dto);
    void deleteSpecies(Long id);
}
