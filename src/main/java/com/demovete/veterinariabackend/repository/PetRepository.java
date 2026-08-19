package com.demovete.veterinariabackend.repository;

import com.demovete.veterinariabackend.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Esta anotacion es opcional
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    //Para buscar las mascotas activas de un cliente
    List<Pet> findByCustomerIdAndActiveTrue(Long customerId);

    //Para validar duplicados buscar por microchip
    Optional<Pet> findByMicrochipNumber(String microchipNumber);

    //Verifica si existe un microchip asignado a otra mascota.
    boolean existsByMicrochipNumber(String microchipNumber);

    //Para no borrar una raza que tenga mascotas asociadas
    boolean existsByBreedId(Long breedId);
}
