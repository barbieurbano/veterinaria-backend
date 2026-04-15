package com.demovete.veterinariabackend.repository;
//asegurarse que se importante estos
import com.demovete.veterinariabackend.model.Owner;
import com.demovete.veterinariabackend.model.OwnerLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Aqui se pueden poner consultas personalizadas
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    //Esta query es sin filtro, solo esta ordenando, podemos agregar que no nos traiga duplicados, paginar.
    @Query("select o from Owner o order by o.lastName")
    List<Owner> findByOrderByLastNameAsc();


}

