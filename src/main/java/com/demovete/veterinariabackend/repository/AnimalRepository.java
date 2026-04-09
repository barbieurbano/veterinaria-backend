package com.demovete.veterinariabackend.repository;

import com.demovete.veterinariabackend.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
//con Extends lo que hace es heredar todos los metodos que tiene JpaRepository
//Long es la Clave Primaria
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}