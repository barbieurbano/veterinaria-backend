package com.demovete.veterinariabackend.service;

import com.demovete.veterinariabackend.dto.SpeciesCreateRequestDTO;
import com.demovete.veterinariabackend.dto.SpeciesResponseDTO;
import com.demovete.veterinariabackend.dto.SpeciesUpdateRequestDTO;
import com.demovete.veterinariabackend.model.Species;
import com.demovete.veterinariabackend.repository.BreedRepository;
import com.demovete.veterinariabackend.repository.SpeciesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpeciesServiceImpl implements SpeciesService {
    private final SpeciesRepository speciesRepository;
    private final BreedRepository breedRepository;

    @Override
    @Transactional
    public SpeciesResponseDTO createSpecies(SpeciesCreateRequestDTO dto) {
        if (speciesRepository.existsByNameIgnoreCase(dto.name())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Ya existe una especie con ese nombre: " + dto.name());
        }
        Species species = Species.builder()
                .name(dto.name())
                .description(dto.description())
                .build();
        return mapToDto(speciesRepository.save(species));
    }

    @Override
    @Transactional
    public SpeciesResponseDTO getSpeciesById(Long id) {
        Species s = speciesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Especie no encontrada con ID: " + id));
        return mapToDto(s);
    }

    @Override
    @Transactional
    public List<SpeciesResponseDTO> getAllSpecies() {
        return speciesRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    @Transactional
    public SpeciesResponseDTO updateSpecies(Long id, SpeciesUpdateRequestDTO dto) {
        Species s = speciesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Especie no encontrada con ID: " + id));


        if (dto.name() != null) {
            //verificar que no de un conflicto con otra especie
            if (!dto.name().equalsIgnoreCase(s.getName())
                    && speciesRepository.existsByNameIgnoreCase(dto.name())) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Ya existe una especie con ese nombre: " + dto.name());
            }
            s.setName(dto.name());
        }
        if (dto.description() != null) s.setDescription(dto.description());

        return mapToDto(speciesRepository.save(s));
    }

    @Override
    @Transactional
    public void deleteSpecies(Long id) {
        Species s = speciesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Especie no encontrada con ID: " + id));

        // no se puede borrar una especie que tiene razas asociadas
        if (breedRepository.existsBySpeciesId(id)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "No se puede borrar: la especie tiene razas asociadas");
        }
        speciesRepository.delete(s);
    }

    private SpeciesResponseDTO mapToDto(Species s) {
        return new SpeciesResponseDTO(
                s.getId(),
                s.getName(),
                s.getDescription()
        );
    }
}
