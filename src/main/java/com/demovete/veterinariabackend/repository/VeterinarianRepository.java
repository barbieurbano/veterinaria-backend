package com.demovete.veterinariabackend.repository;

import com.demovete.veterinariabackend.model.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;
//Cual es la table para que la quieres el repositorio y cual es el ID, Clave primaria y las habiamos puesto en long por eso ahora aparece long
public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {
}