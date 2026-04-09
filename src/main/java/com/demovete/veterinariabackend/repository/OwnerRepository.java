package com.demovete.veterinariabackend.repository;
//asegurarse que se importante estos
import com.demovete.veterinariabackend.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
// Aqui se pueden poner consultas personalizadas
public interface OwnerRepository extends JpaRepository<Owner, Long> {
}

