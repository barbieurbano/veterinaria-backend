package com.demovete.veterinariabackend.repository;

import com.demovete.veterinariabackend.model.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BreedRepository extends JpaRepository<Breed, Long> {
    //Todas las razas de una especie
    List<Breed> findBySpeciesIdOrderByNameAsc(Long speciesId);
    //Para no duplicar razas
    boolean existsByNameIgnoreCaseAndSpeciesId(String name, Long speciesId);
}
