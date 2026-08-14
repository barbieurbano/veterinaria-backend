package com.demovete.veterinariabackend.model;
import com.demovete.veterinariabackend.model.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name = "pets")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pet {
    //1. ATRIBUTOS PROPIOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "microchip_number", unique = true, length = 15)
    private String microchipNumber;

    private Boolean neutered;

    @Column(name = "neutering_date")
    private LocalDate neuteringDate;

    @Column(length = 30)
    private String color;

    @Column(name = "distinctive_marks", length = 255)
    private String distinctiveMarks;

    private String photoUrl;


    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean active = true;

    @Column(columnDefinition = "TEXT")
    private String notes;

    //ATRIBUTOS POR REFERENCIA
    @ManyToOne
    @JoinColumn(name = "breed_id", nullable = false)
    private Breed breed;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;


}
