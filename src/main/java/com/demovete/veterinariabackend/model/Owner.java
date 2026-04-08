package com.demovete.veterinariabackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    //Hacemos que sea independiente el ID a datos del usuario,
    //el dni podria cambiar y entonces deberiamos actualizarlo en todos lados

    private Long id;
    private String firstNombre;
    private String lastName;
    private String dni;

    //Estos son los getters y setters, y metodos que tambien veremos luego.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //TODO [Reverse Engineering] generate columns from DB
}