package com.demovete.veterinariabackend.repository;

import com.demovete.veterinariabackend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //Busca un cliente.
    Optional<Customer> findByDocumentNumber(String documentNumber);

    //Verificar si existe un cliente registrado con ese doc
    boolean existsByDocumentNumber(String documentNumber);

    //Verificar si existe un cliente registrado con ese email.
    boolean existsByEmail(String email);


    //Buscar clientes activos
    List<Customer> findByActiveTrue();

    //Buscar clientes inactivos
    List<Customer> findByActiveFalse();
}
