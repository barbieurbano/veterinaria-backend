package com.demovete.veterinariabackend.api;


import com.demovete.veterinariabackend.dto.SpeciesCreateRequestDTO;
import com.demovete.veterinariabackend.dto.SpeciesResponseDTO;
import com.demovete.veterinariabackend.dto.SpeciesUpdateRequestDTO;
import com.demovete.veterinariabackend.service.SpeciesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/species")
@RequiredArgsConstructor
public class SpeciesRestController {
    private final SpeciesService speciesService;

    @PostMapping
    public ResponseEntity<SpeciesResponseDTO> createSpecies(@Valid @RequestBody SpeciesCreateRequestDTO dto){
        SpeciesResponseDTO created = speciesService.createSpecies(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<SpeciesResponseDTO> getAllSpecies(){
        return speciesService.getAllSpecies();
    }

    @GetMapping("{id}")
    public SpeciesResponseDTO getSpeciesById(@PathVariable Long id,
                                             @Valid @RequestBody SpeciesUpdateRequestDTO dto){
        return speciesService.updateSpecies(id, dto);
    }

    @PutMapping("{id}")
    public SpeciesResponseDTO updateSpecies(@PathVariable Long id,
                                            @Valid @RequestBody SpeciesUpdateRequestDTO dto){
        return speciesService.updateSpecies(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSpecies(@PathVariable Long id){
        speciesService.deleteSpecies(id);
    }

}
