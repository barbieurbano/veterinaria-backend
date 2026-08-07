package com.demovete.veterinariabackend.model;

import com.demovete.veterinariabackend.model.enums.ContractType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;


    private String email;

    @Column(nullable = false, length = 30)
    private Integer phone;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    private Boolean isActive = true;

    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    private BigDecimal salary;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true, nullable = true)
    private User user;

    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY)
    private Veterinarian veterinarianProfile;
}

