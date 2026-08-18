package com.demovete.veterinariabackend.service;

import com.demovete.veterinariabackend.dto.PetCreateRequestDTO;
import com.demovete.veterinariabackend.dto.PetResponseDTO;
import com.demovete.veterinariabackend.dto.PetUpdateRequestDTO;
import com.demovete.veterinariabackend.model.Breed;
import com.demovete.veterinariabackend.model.Customer;
import com.demovete.veterinariabackend.model.Pet;
import com.demovete.veterinariabackend.repository.BreedRepository;
import com.demovete.veterinariabackend.repository.CustomerRepository;
import com.demovete.veterinariabackend.repository.PetRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService{
    private final PetRepository petRepository;
    private final BreedRepository breedRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public PetResponseDTO createPet(PetCreateRequestDTO dto){
        //Validar que existe el cliente
        Customer customer = customerRepository.findById(dto.customerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el cliente con ID: " + dto.customerId()));

        //Validamos si existe la raza
        Breed breed = breedRepository.findById(dto.breedId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la raza con ID: " + dto.breedId()));

        //Si se proporciona el microchip validar que es unico
        if(dto.microchipNumber() != null && !dto.microchipNumber().isBlank()){
            if(petRepository.existsByMicrochipNumber(dto.microchipNumber())){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Ya existe una mascota con este número de microchip" + dto.microchipNumber());
            }
        }

        Pet pet = Pet.builder()
                .name(dto.name())
                .birthDate(dto.birthDate())
                .gender(dto.gender())
                .microchipNumber(dto.microchipNumber())
                .neutered(dto.neutered())
                .neuteringDate(dto.neuteringDate())
                .color(dto.color())
                .distinctiveMarks(dto.distinctiveMarks())
                .photoUrl(dto.photoUrl())
                .notes(dto.notes())
                .breed(breed)
                .customer(customer)
                .build();

        Pet savedPet = petRepository.save(pet);

        return mapToDto(savedPet);
    }

    @Override
    @Transactional
    public PetResponseDTO getPetById(Long id){
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Mascota no encontrada con ID: " + id));
        return mapToDto(pet);
    }

    @Override
    @Transactional
    public List<PetResponseDTO> getPetsByCustomerId(Long customerId){
        if(!customerRepository.existsById(customerId)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "El cliente con ID " + customerId + "no existe.");

        }
        return petRepository.findByCustomerIdAndActiveTrue(customerId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    @Transactional
    public PetResponseDTO updatePet(Long id, PetUpdateRequestDTO dto){
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Mascota no encontrada con ID: " + id));

        if (dto.name() != null)            pet.setName(dto.name());
        if (dto.birthDate() != null)       pet.setBirthDate(dto.birthDate());
        if (dto.gender() != null)          pet.setGender(dto.gender());
        if (dto.neutered() != null)        pet.setNeutered(dto.neutered());
        if (dto.neuteringDate() != null)   pet.setNeuteringDate(dto.neuteringDate());
        if (dto.color() != null)           pet.setColor(dto.color());
        if (dto.distinctiveMark() != null) pet.setDistinctiveMarks(dto.distinctiveMark());
        if (dto.photoUrl() != null)        pet.setPhotoUrl(dto.photoUrl());
        if (dto.active() != null)          pet.setActive(dto.active());
        if (dto.notes() != null)           pet.setNotes(dto.notes());


        if (dto.microchipNumber() != null && !dto.microchipNumber().isBlank()) {
            petRepository.findByMicrochipNumber(dto.microchipNumber())
                    .filter(other -> !other.getId().equals(id))
                    .ifPresent(other -> { throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "Ese microchip ya pertenece a otra mascota"); });
            pet.setMicrochipNumber(dto.microchipNumber());
        }

        // Traemos la entidad
        if (dto.breedId() != null) {
            Breed breed = breedRepository.findById(dto.breedId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "No se encontró la raza con ID: " + dto.breedId()));
            pet.setBreed(breed);
        }
        if (dto.customerId() != null) {
            Customer customer = customerRepository.findById(dto.customerId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "No se encontró el cliente con ID: " + dto.customerId()));
            pet.setCustomer(customer);
        }

        Pet updated = petRepository.save(pet);
        return mapToDto(updated);
    }

    @Override
    @Transactional
    public void deletePet(Long id){
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Mascota no encontrada con ID: " + id));
        pet.setActive(false);
        petRepository.save(pet);
    }

    private PetResponseDTO mapToDto(Pet pet){
        return new PetResponseDTO(
          pet.getId(),
          pet.getName(),
          pet.getBirthDate(),
          pet.getGender(),
          pet.getMicrochipNumber(),
          pet.getNeutered(),
          pet.getNeuteringDate(),
          pet.getColor(),
          pet.getDistinctiveMarks(),
          pet.getPhotoUrl(),
          pet.getActive(),
          pet.getNotes(),

          pet.getBreed().getId(),
          pet.getBreed().getName(),
          pet.getBreed().getSpecies().getName(),

          pet.getCustomer().getId(),
pet.getCustomer().getFirstName() + " " + pet.getCustomer().getLastName()

        );
    }
}

