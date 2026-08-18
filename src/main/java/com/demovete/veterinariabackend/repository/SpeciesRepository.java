package com.demovete.veterinariabackend.repository;

import com.demovete.veterinariabackend.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepository extends JpaRepository<Species, Long> {
    boolean existsByNameIgnoreCase(String name);
}
