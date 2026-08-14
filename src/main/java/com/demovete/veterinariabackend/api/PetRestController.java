package com.demovete.veterinariabackend.api;

import com.demovete.veterinariabackend.dto.PetCreateRequestDTO;
import com.demovete.veterinariabackend.dto.PetResponseDTO;
import com.demovete.veterinariabackend.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PetRestController {
    private final PetService petService;

    @PostMapping
    public ResponseEntity<PetResponseDTO> createPet(@Valid @RequestBody PetCreateRequestDTO dto){
        PetResponseDTO createdPet = petService.createPet(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPet);
    }

    @GetMapping("{id}")
    public PetResponseDTO getPetById(@PathVariable Long id){
        return petService.getPetById(id);
    }
}
